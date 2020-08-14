package com.example.demo.repository;

import com.example.demo.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;

@Repository
public interface FileRepository extends JpaRepository<File,Integer> {

    File save(File file);

    File getByFileName(String name);

    File getByUploadLocationLike(String like);
}
