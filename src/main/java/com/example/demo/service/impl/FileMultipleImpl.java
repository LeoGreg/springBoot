package com.example.demo.service.impl;

import com.example.demo.model.File;
import com.example.demo.util.component.FileLocationToStore;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.abstraction.FileMultipleService;
import com.example.demo.util.exception.file.FileNotFoundException;
import com.example.demo.util.exception.file.LeakageException;
import com.example.demo.util.exception.file.NoDirectoryException;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Log4j2
@Service
public class FileMultipleImpl implements FileMultipleService {

    @Autowired
    private FileRepository fileRepository;

    private final Path path;


    @Autowired
    public FileMultipleImpl(FileLocationToStore fileLocationToStore) throws NoDirectoryException {

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
    @Async("fileMultipleUpload")
    public File storeMultiple(MultipartFile file) throws FileNotFoundException, LeakageException {
        try {
            log.info("time {}",new Date());
            LeakageException.isOutOfLimit(file.getSize() > 2 * 125000000, "leakage");
            log.info(">>>thread name {} ,id {}", Thread.currentThread().getName(), Thread.currentThread().getId());
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
        } catch (IOException  e) {
            e.printStackTrace();
            log.info(">>>cannot store file:");
            throw new FileNotFoundException("could.not.find.a.file", e);

        }
    }

}
