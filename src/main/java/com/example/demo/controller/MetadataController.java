package com.example.demo.controller;

import com.example.demo.model.Metadata;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.service.abstraction.MetadataService;
import com.example.demo.util.exception.file.LeakageException;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.demo.util.constants.fileHelper.DownloadConst.*;
import static com.example.demo.util.constants.fileHelper.SubCon.*;

@Log4j2
@RestController
@RequestMapping("/metadata")
public class MetadataController {


    @Autowired
    private MetadataRepository metadataRepository;

    @Autowired
    private MetadataService fileStoreHelper;


    @PostMapping()
    public ResponseEntity add(@RequestParam(FILE_NAME) MultipartFile multipartFile) throws IOException, LeakageException {
        Metadata metadata = fileStoreHelper.store(multipartFile);
        Map info = new HashMap();
        info.put(ID, metadata.getId());
        info.put(PATH, metadata.getPath());
        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.OK).body(info);
        return responseEntity;
    }


    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) throws UsernameNotFoundException, IOException {
        Metadata metadata = metadataRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(EMPTY));
        FileSystemResource fileSystemResource = new FileSystemResource(metadata.getPath());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header(LOOK_OR_LOAD_HEADER, LOAD_ITEM)
                //  .header("Content-Disposition","attachment; filename=file.jpg")//download
                .contentLength(fileSystemResource.contentLength())
                .body(fileSystemResource);
    }


}