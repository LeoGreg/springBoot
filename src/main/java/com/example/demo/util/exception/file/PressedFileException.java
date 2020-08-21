package com.example.demo.util.exception.file;

import com.example.demo.service.impl.MetaDataServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PressedFileException extends Exception {

    public PressedFileException(String message) {
        super(message);
    }

    public static void isOverFilled(boolean e, String m) throws PressedFileException {
        if (e) {
            throw new PressedFileException(m);
        }
    }
}
