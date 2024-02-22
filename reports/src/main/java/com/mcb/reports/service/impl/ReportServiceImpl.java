package com.mcb.reports.service.impl;

import com.mcb.reports.entities.views.PvsValuationRequestViews;
import com.mcb.reports.repository.PropertyValuationRepository;
import com.mcb.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PropertyValuationRepository propertyValuationRepository;

    @Override
    public List<PvsValuationRequestViews> searchPropertyValuations(String reference, LocalDateTime createdAt, String fosRef) {
        return propertyValuationRepository.findCustomProjection(reference, createdAt, fosRef);
    }
}
