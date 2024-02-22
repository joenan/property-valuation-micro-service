package com.mcb.app.service;



import com.mcb.commons.entities.FileUpload;

import java.util.List;

public interface FileUploadService {
    FileUpload saveFileUpload(FileUpload fileUpload);
    FileUpload getFileUploadById(Long id);
    List<FileUpload> getAllFileUploads();
    void deleteFileUpload(Long id);
}