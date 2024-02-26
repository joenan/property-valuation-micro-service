package com.mcb.reports.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class SearchParamsDTO {
    private String reference;
    private LocalDateTime createdAt;
    private String fosRef;
}