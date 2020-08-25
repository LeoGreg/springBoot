package com.example.demo.service.abstraction;

import com.example.demo.model.Metadata;
import com.example.demo.util.exception.file.LeakageException;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

public interface MetadataService {
    @Transactional
    String buildName(String extension);

    @Transactional
    Metadata store(MultipartFile file) throws IOException, LeakageException;
}
