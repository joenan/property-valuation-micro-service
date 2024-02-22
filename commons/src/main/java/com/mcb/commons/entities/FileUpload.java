package com.mcb.commons.entities;


import com.mcb.commons.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FileUpload implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DocumentType documentType;
    private long fileSize;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User uploadedBy;
    @ManyToOne
    @JoinColumn(name = "property_valuation_id")
    private PropertyValuation propertyValuation;
    private LocalDateTime uploadedDate;
}
