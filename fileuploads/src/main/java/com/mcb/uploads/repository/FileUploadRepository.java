package com.mcb.uploads.repository;



import com.mcb.commons.entities.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}