package com.mcb.uploads.controller;


import com.mcb.commons.enums.DocumentType;
import com.mcb.uploads.dto.FileUploadDto;
import com.mcb.uploads.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file-uploads")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping
    public ResponseEntity<FileUploadDto> uploadFile(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("documentType") DocumentType documentType,
                                                    @RequestParam("userId") Long userId,
                                                    @RequestParam("propertyValuationId") Long propertyValuationId) {
        String username = getLoggedInUsername();

        FileUploadDto uploadedFile = fileUploadService.uploadFile(file, documentType, userId, propertyValuationId, username);
        return ResponseEntity.ok(uploadedFile);
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId) {

        FileUploadDto fileUploadDto = fileUploadService.getFileUploadById(fileId);

        // Download the file from S3 using the service method
        byte[] fileContent = fileUploadService.download(fileUploadDto.getFileName());

        // Set up the response headers
        HttpHeaders headers = new HttpHeaders();

        // Extract the file extension from the file name
        String fileName = fileUploadDto.getFileName();
        String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1);

        // Append the file extension to the file name for Content-Disposition
        headers.setContentDispositionFormData("attachment", fileName);
        headers.set("Content-Type", "application/" + fileExtension);
        headers.setContentLength(fileContent.length);


        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }



    @GetMapping("/{id}")
    public ResponseEntity<FileUploadDto> getFileUploadById(@PathVariable Long id) {
        FileUploadDto fileUploadDto = fileUploadService.getFileUploadById(id);
        return ResponseEntity.ok(fileUploadDto);
    }
    @GetMapping
    public List<FileUploadDto> getAllFileUploads() {
        return fileUploadService.getAllFileUploads();
    }

    @GetMapping("/property/{propertyId}")
    public List<FileUploadDto> getAllFileUploadsByUserIdAndPropertyId(@PathVariable Long propertyId) {
        String username = getLoggedInUsername();
        return fileUploadService.getAllFileUploadsByUserNameAndPropertyId(username, propertyId);
    }

    @DeleteMapping("/{id}")
    public void deleteFileUpload(@PathVariable Long id) {
        fileUploadService.deleteFileUpload(id);
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
