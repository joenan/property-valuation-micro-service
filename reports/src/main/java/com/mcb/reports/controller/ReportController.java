package com.mcb.reports.controller;

import com.mcb.reports.entities.views.PvsValuationRequestViews;
import com.mcb.reports.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/reports/")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/pvs-valuation-request")
    public List<PvsValuationRequestViews> searchPropertyValuations(
            @RequestParam(name = "reference", required = false) String reference,
            @RequestParam(name = "createdAt", required = false) LocalDateTime createdAt,
            @RequestParam(name = "fosRef", required = false) String fosRef) {
        return reportService.searchPropertyValuations(reference, createdAt, fosRef);
    }
}
