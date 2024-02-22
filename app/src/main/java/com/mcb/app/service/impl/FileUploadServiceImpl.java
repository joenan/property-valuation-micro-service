package com.mcb.app.service.impl;

import com.mcb.app.repository.FileUploadRepository;
import com.mcb.app.service.FileUploadService;
import com.mcb.commons.entities.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {


    private final FileUploadRepository fileUploadRepository;

    @Override
    public FileUpload saveFileUpload(FileUpload fileUpload) {
        return fileUploadRepository.save(fileUpload);
    }

    @Override
    public FileUpload getFileUploadById(Long id) {
        return fileUploadRepository.findById(id).orElse(null);
    }

    @Override
    public List<FileUpload> getAllFileUploads() {
        return fileUploadRepository.findAll();
    }

    @Override
    public void deleteFileUpload(Long id) {
        fileUploadRepository.deleteById(id);
    }
}