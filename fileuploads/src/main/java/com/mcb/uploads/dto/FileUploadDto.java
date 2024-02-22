package com.mcb.uploads.dto;


import com.mcb.commons.enums.DocumentType;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadDto {
    private Long id;
    private DocumentType documentType;
    private long fileSize;
    private String fileName;
    private LocalDateTime uploadedDate;
}
