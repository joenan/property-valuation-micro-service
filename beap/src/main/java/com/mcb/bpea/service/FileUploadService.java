package com.mcb.bpea.service;

import com.mcb.bpea.entities.FileUpload;

import java.util.List;

public interface FileUploadService {
    FileUpload saveFileUpload(FileUpload fileUpload);
    FileUpload getFileUploadById(Long id);
    List<FileUpload> getAllFileUploads();
    void deleteFileUpload(Long id);
}