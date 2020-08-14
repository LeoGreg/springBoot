package com.example.demo.controller;

import com.example.demo.model.File;
import com.example.demo.model.info.LocationToStore;
import com.example.demo.model.info.SubCon;
import com.example.demo.service.abstraction.FileHandlerService;
import com.example.demo.util.exception.file.LeakageException;
import com.example.demo.util.exception.file.MultipartFilesOutOfOrderException;
import com.example.demo.util.exception.file.NoResourceException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;


@Data
@Log4j2
@RestController
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FileHandlerService fileHandlerService;
    @Autowired
    private LocationToStore locationToStore;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/up")
    public ResponseEntity upload(@RequestParam(SubCon.FILE_NAME) MultipartFile file) throws LeakageException {
        log.info(">>>thread name : {} ,id {}", Thread.currentThread().getName(), Thread.currentThread().getId());
        log.info(">>>in up method:");
        File demoFile = fileHandlerService.store(file);
        log.info(">>>after store:");
       /* String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(demoFile.getFileName())
                .toUriString();*/
        Map<String, String> info = new Hashtable<>();
        info.put("location :", locationToStore.getLoc());
        info.put("name :", locationToStore.getLoc().substring(SubCon.SUB_NUM));
        ResponseEntity bodyBuilder = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(info);
        return bodyBuilder;

    }

    @PostMapping("/upFiles")
    public ResponseEntity uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) throws LeakageException {
        fileHandlerService.checkCountBeforeStore(files);
        Map<String, String> info = new Hashtable<>();
        info.put("location :", locationToStore.getLoc());
        for (int i = 0; i < files.length; i++) {
            info.put("file name number :" + (i + 1), files[i].getOriginalFilename());
        }
        ResponseEntity bodyBuilder = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(info);
        return bodyBuilder;

    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) throws NoResourceException {
        Resource resource = fileHandlerService.loadFile(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
