package com.example.demo.util.exception.file;

import com.example.demo.util.exception.user.userException.DuplicateUserException;

public class MultipartFilesOutOfOrderException extends Exception {

    public MultipartFilesOutOfOrderException(String message) {
        super(message);
    }

    public static void check(boolean ex, String message) throws MultipartFilesOutOfOrderException {
        if (ex) {
            throw new MultipartFilesOutOfOrderException(message);
        }
    }
}
