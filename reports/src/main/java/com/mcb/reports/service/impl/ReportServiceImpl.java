package com.mcb.reports.service.impl;

import com.mcb.reports.entities.views.PvsValuationRequestViews;
import com.mcb.reports.repository.PropertyValuationRepository;
import com.mcb.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final PropertyValuationRepository propertyValuationRepository;

    @Override
    public List<PvsValuationRequestViews> searchPropertyValuations(String reference, LocalDateTime createdAt, String fosRef) {

        List<Object[]> result = propertyValuationRepository.findCustomProjection(reference, createdAt, fosRef);

        List<PvsValuationRequestViews> pvsValuationRequestViewsList = result.stream()
                .map(row -> {
                    PvsValuationRequestViews pvsValuationRequestViews = new PvsValuationRequestViews();
                    pvsValuationRequestViews.setId((Long) row[0]);
                    pvsValuationRequestViews.setReference((String) row[1]);
                    pvsValuationRequestViews.setReceivedOn((LocalDateTime) row[2]);
                    pvsValuationRequestViews.setBorrowersName((String) row[3]);
                    pvsValuationRequestViews.setFosRef((String) row[4]);
                    pvsValuationRequestViews.setCreatedOn((LocalDateTime) row[5]);
                    pvsValuationRequestViews.setModifiedOn((LocalDateTime) row[6]);
                    return pvsValuationRequestViews;
                })
                .toList();

        return pvsValuationRequestViewsList;
    }
}
