package com.mcb.uploads.service;

import com.mcb.commons.entities.FileUpload;
import com.mcb.commons.enums.DocumentType;
import com.mcb.uploads.dto.FileUploadDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploadService {
    FileUploadDto getFileUploadById(Long id);
    List<FileUpload> getAllFileUploads();
    void deleteFileUpload(Long id);
    FileUploadDto uploadFile(MultipartFile file,
                             DocumentType documentType,
                             Long userId,
                             Long propertyValuationId,
                             String username);

    byte[] download(String fileName);
}