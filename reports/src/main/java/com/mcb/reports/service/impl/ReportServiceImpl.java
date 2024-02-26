package com.mcb.reports.service.impl;

import com.mcb.reports.entities.views.PvsValuationRequestViews;
import com.mcb.reports.repository.PropertyValuationRepository;
import com.mcb.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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
                    pvsValuationRequestViews.setReceivedOn(((Timestamp) row[2]).toLocalDateTime());
                    pvsValuationRequestViews.setBorrowersName((String) row[3]);
                    pvsValuationRequestViews.setFosRef((String) row[4]);
                    pvsValuationRequestViews.setCreatedOn(((Timestamp) row[5]).toLocalDateTime());
                    pvsValuationRequestViews.setModifiedOn(((Timestamp) row[6]).toLocalDateTime());
                    return pvsValuationRequestViews;
                })
                .toList();


        return pvsValuationRequestViewsList;
    }
}
