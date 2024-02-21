package com.mcb.bpea.repository;

import com.mcb.bpea.entities.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
}