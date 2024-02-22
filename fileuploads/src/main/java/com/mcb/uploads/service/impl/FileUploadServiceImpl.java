package com.mcb.uploads.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.mcb.commons.entities.FileUpload;
import com.mcb.commons.entities.User;
import com.mcb.commons.enums.DocumentType;
import com.mcb.uploads.dto.FileUploadDto;
import com.mcb.uploads.repository.FileUploadRepository;
import com.mcb.uploads.repository.UserRepository;
import com.mcb.uploads.service.FileUploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String BUCKET_NAME;

    private final UserRepository userRepository;

    @Override
    @Transactional
    public FileUploadDto uploadFile(MultipartFile file,
                                    DocumentType documentType,
                                    Long userId,
                                    Long propertyValuationId,
                                    String username) {
        try {
            // Upload the file to S3

            User user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));


            String modifiedName = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "");
            String s3Url = uploadToS3(file);

            FileUpload fileUpload = FileUpload.builder()
                    .documentType(documentType)
                    .fileSize(file.getSize())
                    .fileName(modifiedName)
                    .uploadedBy(user)
                    .uploadedDate(LocalDateTime.now(ZoneOffset.UTC))
                    .build();

            FileUpload savedFile = fileUploadRepository.save(fileUpload);

            return mapToFileUploadDto(savedFile);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading file");
        }
    }

    @Override
    public byte[] download(String fileName) {
        try {
            S3Object s3Object = amazonS3.getObject(BUCKET_NAME, fileName);
            S3ObjectInputStream inputStream = s3Object.getObjectContent();

            // Read the file content into a byte array
            return IOUtils.toByteArray(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error downloading file from S3");
        }
    }

    private String uploadToS3(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            String key = Objects.requireNonNull(file.getOriginalFilename()).replaceAll("\\s+", "");

            // Add any additional metadata if needed
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());

            PutObjectResult putObjectResult = amazonS3.putObject(BUCKET_NAME, key, inputStream, metadata);

            // Optionally, you can return the S3 URL
            return amazonS3.getUrl(BUCKET_NAME, key).toString();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading file to S3");
        }
    }

    public S3Object download(String path, String fileName) {
        return amazonS3.getObject(path, fileName);
    }

    @Override
    public FileUploadDto getFileUploadById(Long id) {
        FileUpload fileUpload = fileUploadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id: " + id));
        return mapToFileUploadDto(fileUpload);
    }

    @Override
    public List<FileUpload> getAllFileUploads() {
        return fileUploadRepository.findAll();
    }

    @Override
    public void deleteFileUpload(Long id) {
        fileUploadRepository.deleteById(id);
    }

    public FileUploadDto mapToFileUploadDto(FileUpload upload) {
        FileUploadDto fileUploadDto = new FileUploadDto();
        fileUploadDto.setId(upload.getId());
        fileUploadDto.setDocumentType(upload.getDocumentType());
        fileUploadDto.setFileSize(upload.getFileSize());
        fileUploadDto.setFileName(upload.getFileName());
        fileUploadDto.setUploadedDate(upload.getUploadedDate());
        return fileUploadDto;
    }
}
