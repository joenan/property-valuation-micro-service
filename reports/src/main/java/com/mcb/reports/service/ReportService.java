package com.mcb.reports.service;

import com.mcb.reports.dto.PropertyValuationProjection;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<PropertyValuationProjection> searchPropertyValuations(String reference, LocalDateTime createdAt, String fosRef);
}
