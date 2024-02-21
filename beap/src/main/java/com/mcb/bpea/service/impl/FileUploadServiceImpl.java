package com.mcb.bpea.service.impl;

import com.mcb.bpea.entities.FileUpload;
import com.mcb.bpea.repository.FileUploadRepository;
import com.mcb.bpea.service.FileUploadService;
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