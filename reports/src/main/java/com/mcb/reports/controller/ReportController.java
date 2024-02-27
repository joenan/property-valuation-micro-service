package com.mcb.reports.controller;


import com.mcb.commons.dto.SearchParamsDTO;
import com.mcb.commons.entities.PvsValuationRequestViews;
import com.mcb.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/reports/")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("/pvs-valuation-request")
    public List<PvsValuationRequestViews> searchPropertyValuations(@RequestBody SearchParamsDTO searchParamsDTO) {
        String reference = searchParamsDTO.getReference();
        LocalDateTime createdAt = searchParamsDTO.getCreatedAt();
        String fosRef = searchParamsDTO.getFosRef();

        return reportService.searchPropertyValuations(reference, createdAt, fosRef);
    }
}
