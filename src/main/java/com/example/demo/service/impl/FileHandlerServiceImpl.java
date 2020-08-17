package com.example.demo.service.impl;

import com.example.demo.model.File;
import com.example.demo.util.component.FileLocationToStore;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.abstraction.FileHandlerService;
import com.example.demo.service.abstraction.FileMultipleService;
import com.example.demo.util.exception.file.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@Data
@Log4j2
@Service
public class FileHandlerServiceImpl implements FileHandlerService {


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMultipleService fileMultipleService;


    private final Path path;


    @Autowired
    public FileHandlerServiceImpl(FileLocationToStore fileLocationToStore) throws NoDirectoryException {

        this.path = Paths.get(fileLocationToStore.getLoc()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(">>>couldn't create directory:");
            throw new NoDirectoryException("something went wrong", e);
        }
    }


    @Override
    @Transactional
    public File store(MultipartFile file) throws FileNotFoundException, LeakageException {
        try {
            LeakageException.isOutOfLimit(file.getSize() < 1024 * 1024, "leakage");
            log.info(">>>thread name in store {} ,id {}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info(">>>size {} ", file.getSize());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            log.info(">>>fileName {}", fileName);
            Path targetLocation = this.path.resolve(fileName);
            log.info(">>>location {}", targetLocation.toString());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Map<String, String> info = new HashMap<>();
            info.put(fileName, targetLocation.toString());
            File demoDbFile = new File();
            demoDbFile.setFileName(fileName);
            demoDbFile.setUploadLocation(targetLocation.toString());
            Thread.currentThread().interrupt();//??????
            log.info("running thread after interrupt {} , id {}", Thread.currentThread().getName(), Thread.currentThread().getId());
            return fileRepository.save(demoDbFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(">>>can't store file:");
            throw new FileNotFoundException("could't find a file :", e);

        }
    }

    @Override
    @Transactional
    public Resource loadFile(String filename) throws NoResourceException, RuntimeException {
        try {
            Path path = this.path.resolve(filename).normalize();
            Resource resource = new UrlResource(path.toUri());
            NoResourceException.check(resource == null, "there is no such a file");
            return resource;
        } catch (MalformedURLException e) {
            log.warn(">>>uri exception {} ", e);
            throw new RuntimeException("something is wrong with uri ");
        }
    }

  /*  @Override
    @Async("fileMultipleUpload")
    public File storeMultiple(MultipartFile file) throws FileNotFoundException, LeakageException {
        try {
            LeakageException.isOutOfLimit(file.getSize() > 2 * 125000000, "leakage");
            log.info(">>>thread name in store {} ,id {}", Thread.currentThread().getName(), Thread.currentThread().getId());
            log.info(">>>size {} ", file.getSize());
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            log.info(">>>fileName {}", fileName);
            Path targetLocation = this.path.resolve(fileName);
            log.info(">>>location {}", targetLocation.toString());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            Map<String, String> info = new HashMap<>();
            info.put(fileName, targetLocation.toString());
            File demoDbFile = new File();
            demoDbFile.setFileName(fileName);
            demoDbFile.setUploadLocation(targetLocation.toString());
            return fileRepository.save(demoDbFile);
        } catch (IOException e) {
            e.printStackTrace();
            log.info(">>>cannot store file:");
            throw new FileNotFoundException("could.not.find.a.file", e);

        }
    }*/

   /* @Override
    public void checkCountBeforeStore(MultipartFile[] files) throws  LeakageException {
        *//*MultipartFilesOutOfOrderException.check(files.length > 5, "upload.files.outrun");*//*
        for(int i=0;i<files.length;i++){
            storeMultiple(files[i]);
        }
    }*/

    @Override
    public void checkCountBeforeStore(MultipartFile[] files) throws LeakageException {
        for(int i=0;i<files.length;i++){
            fileMultipleService.storeMultiple(files[i]);
        }
    }

    @Override
    @Transactional
    public void delete(int id){
        fileRepository.deleteById(id);
    }

}
