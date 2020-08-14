package com.example.demo.service.abstraction;

import com.example.demo.model.File;
import com.example.demo.util.exception.file.FileNotFoundException;
import com.example.demo.util.exception.file.LeakageException;
import com.example.demo.util.exception.file.MultipartFilesOutOfOrderException;
import com.example.demo.util.exception.file.NoResourceException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

public interface FileHandlerService {
    @Transactional
    File store(MultipartFile file) throws LeakageException;

    @Transactional
    Resource loadFile(String filename) throws NoResourceException, RuntimeException;

    void checkCountBeforeStore(MultipartFile[] files) throws LeakageException;

    void delete(int id);

  /*  @Transactional
    File storeMultiple(MultipartFile file) throws FileNotFoundException, LeakageException, MultipartFilesOutOfOrderException;

    void checkCountBeforeStore(MultipartFile[] files) throws  LeakageException;
}*/
}