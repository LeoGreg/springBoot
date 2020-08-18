/*package com.example.demo.util;*/

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/*
@Component
*/
/*
public class FileStoreHelper {

    private String root = "C:\\Users\\User\\Desktop\\uploads\\";

    public String buildName(String extension) {
        Calendar calendar = Calendar.getInstance();
        return root + calendar.get(Calendar.YEAR) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH)
                + "/" + UUID.randomUUID().toString() + "." + extension;
    }



    public String storeFile(File file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getName());
        String fileName = buildName(extension);
        FileUtils.copyFile(file,new File(fileName));
        return fileName;
    }

    public String storeFile(MultipartFile multipartFile) throws IOException {
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String fileName = buildName(extension);
        FileUtils.writeByteArrayToFile(new File(fileName), multipartFile.getBytes());
        return fileName;
    }
*/
/*
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
​
​
public enum DocumentType {
​
​
    PLAIN_TEXT("text/plain", "txt"),
​
    OTHER("application/octet-stream", "any"),
​
    PDF("application/pdf", "pdf"),
​
    XML("application/xml", "xml", "xsd"),
​
    JSON("application/json", "json"),
​
    ZIP("application/zip", "zip", "zipx"),
​
    GZIP("application/gzip", "gz", "tgz", "tar.gz"),
​
    DOC("application/msword", "doc", "docx"),
​
    IMAGE_PNG("image/png", "png"),
​
    IMAGE_JPEG("image/jpeg", "jpg", "jfif", "jpe", "jpeg"),
​
    IMAGE_GIF("image/gif", "gif"),
​
    VIDEO_MP4("video/mp4", "mp4"),
​
    VIDEO_MPEG("video/mpeg", "mpg", "mpeg", "mp1", "mp2", "mp3", "m1v", "mpv", "m1a", "m2a", "mpa"),
​
    VIDEO_OGG("video/ogg", "ogg", "ogv", "oga", "ogx", "spx", "opus", "ogm"),
​
    VIDEO_WEBM("video/webm", "webm"),
​
    VIDEO_FLV("video/x-flv", "flv");
​
​
    private String mimeType;
​
    private Set<String> extensions;
​
​
    DocumentType(String mimeType, String... extensions) {
        this.mimeType = mimeType;
        this.extensions = new HashSet<>(Arrays.asList(extensions));
    }
​
    public static DocumentType valueOfExtension(String extension) {
        for (DocumentType documentType : values()) {
            if (documentType.getExtensions().contains(extension)) {
                return documentType;
            }
        }
        return DocumentType.OTHER;
    }
​
​
    public String getMimeType() {
        return mimeType;
    }
​
​
    public Set<String> getExtensions() {
        return extensions;
    }
​
​
}
 */