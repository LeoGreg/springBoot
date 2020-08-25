package com.example.demo.service.impl;

import com.example.demo.model.Metadata;
import com.example.demo.repository.MetadataRepository;
import com.example.demo.service.abstraction.MetadataService;
import com.example.demo.util.constants.fileHelper.meta.Meta;
import com.example.demo.util.exception.file.FileNotFoundException;
import com.example.demo.util.exception.file.LeakageException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;


@Service
public class MetadataServiceImpl implements MetadataService {

    @Resource
    private MetadataRepository metadataRepository;

    private String root = "C:/Users/User/Desktop/up";


    @Override
    @Transactional
    public String buildName(String extension) {
        Calendar calendar = Calendar.getInstance();
        return root + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" +
                java.util.UUID.randomUUID().toString() + "." + extension;
    }

    @Override
    @Transactional
    public Metadata store(MultipartFile file) throws IOException, LeakageException {
        LeakageException.isOutOfLimit(file.getSize()>= Meta.MAX_UP,"leakage");
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String path_file_name = buildName(extension);
        FileUtils.writeByteArrayToFile(new File(path_file_name), file.getBytes());
        Metadata metadata = new Metadata();
        metadata.setPath(path_file_name);
        metadataRepository.save(metadata);
        return metadata;
    }
}
