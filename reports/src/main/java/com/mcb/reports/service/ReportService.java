package com.mcb.reports.service;


import com.mcb.commons.entities.PvsValuationRequestViews;

import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<PvsValuationRequestViews> searchPropertyValuations(String reference, LocalDateTime createdAt, String fosRef);
}
