package com.example.demo.service.abstraction;

import com.example.demo.model.File;
import com.example.demo.util.exception.file.FileNotFoundException;
import com.example.demo.util.exception.file.LeakageException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

public interface FileMultipleService {
    @Async("fileMultipleUpload")
    File storeMultiple(MultipartFile file) throws FileNotFoundException, LeakageException;

}
