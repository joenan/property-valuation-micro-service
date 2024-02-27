package com.mcb.uploads.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.mcb.commons.entities.FileUpload;
import com.mcb.commons.entities.PropertyValuation;
import com.mcb.commons.entities.User;
import com.mcb.commons.enums.DocumentType;
import com.mcb.commons.exception.ResourceNotFoundException;
import com.mcb.uploads.dto.FileUploadDto;
import com.mcb.uploads.repository.FileUploadRepository;
import com.mcb.uploads.repository.PropertyValuationRepository;
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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class FileUploadServiceImpl implements FileUploadService {

    private final FileUploadRepository fileUploadRepository;

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket.name}")
    private String BUCKET_NAME;

    private static final long MAX_FILE_SIZE_BYTES = 3 * 1024 * 1024; // 3MB in bytes

    private final UserRepository userRepository;

    private final PropertyValuationRepository propertyValuationRepository;

    @Override
    @Transactional
    public FileUploadDto uploadFile(MultipartFile file,
                                    DocumentType documentType,
                                    Long userId,
                                    Long propertyValuationId,
                                    String username) {
        try {

            // Check file size
            if (file.getSize() > MAX_FILE_SIZE_BYTES) {
                throw new IllegalArgumentException("File size exceeds the allowed limit (3MB)");
            }

            // Check allowed file types
            String originalFilename = Objects.requireNonNull(file.getOriginalFilename());
            String fileExtension = getFileExtension(originalFilename).toLowerCase();
            List<String> allowedExtensions = Arrays.asList("pdf", "doc", "docx", "jpg", "png");

            if (!allowedExtensions.contains(fileExtension)) {
                throw new IllegalArgumentException("Only these file types are allowed" +allowedExtensions);
            }

            // Upload the file to S3
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

            String modifiedName = originalFilename.replaceAll("\\s+", "");
            String s3Url = uploadToS3(file);

            PropertyValuation propertyValuation = propertyValuationRepository
                    .findById(propertyValuationId)
                    .orElseThrow(() -> new ResourceNotFoundException("Property not found"));

            String fileSizeWithUnit = calculateFileSizeWithUnit(file.getSize());

            FileUpload fileUpload = FileUpload.builder()
                    .documentType(documentType)
                    .fileSize(fileSizeWithUnit)
                    .fileName(modifiedName)
                    .uploadedBy(user)
                    .propertyValuation(propertyValuation)
                    .uploadedDate(LocalDateTime.now(ZoneOffset.UTC))
                    .build();

            FileUpload savedFile = fileUploadRepository.save(fileUpload);

            FileUploadDto fileUploadDto = mapToFileUploadDto(savedFile);

            return fileUploadDto;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error uploading file");
        }
    }

    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("File has no extension");
        }
        return filename.substring(lastDotIndex + 1);
    }

    private String calculateFileSizeWithUnit(long fileSizeInBytes) {
        double fileSizeInKB = fileSizeInBytes / 1024.0;
        double fileSizeInMB = fileSizeInKB / 1024.0;
        double fileSizeInGB = fileSizeInMB / 1024.0;

        if (fileSizeInGB >= 1) {
            return String.format("%.2f GB", fileSizeInGB);
        } else if (fileSizeInMB >= 1) {
            return String.format("%.2f MB", fileSizeInMB);
        } else {
            return String.format("%.2f KB", fileSizeInKB);
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

    @Override
    public List<FileUploadDto> getAllFileUploadsByUserNameAndPropertyId(String username, Long propertyId) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return fileUploadRepository.findFileUploadsByUploadedByAndPropertyId(user.getId(), propertyId)
                .stream().map(this::mapToFileUploadDto).collect(Collectors.toList());
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
    public List<FileUploadDto> getAllFileUploads() {
        return fileUploadRepository.findAll()
                .stream()
                .map(this::mapToFileUploadDto)
                .collect(Collectors.toList());
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
        fileUploadDto.setPropertyValuationId(upload.getPropertyValuation().getId());
        fileUploadDto.setUploadedBy(upload.getUploadedBy().getName());
        return fileUploadDto;
    }
}
