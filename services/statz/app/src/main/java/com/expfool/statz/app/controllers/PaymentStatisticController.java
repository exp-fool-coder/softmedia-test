package com.expfool.statz.app.controllers;

import com.expfool.bookkeeper.api.Services.BookkeeperService;
import com.expfool.statz.app.dto.GetClientCategoriesStatisticResponse;
import com.expfool.statz.app.managers.StatisticManager;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/statistic")
public class PaymentStatisticController {

    private final BookkeeperService bookkeeperService;
    private final StatisticManager statisticManager;

    public PaymentStatisticController(
            BookkeeperService bookkeeperService,
            StatisticManager statisticManager
    ) {
        this.bookkeeperService = bookkeeperService;
        this.statisticManager = statisticManager;
    }

    @GetMapping("/client/{clientId}")
    public GetClientCategoriesStatisticResponse getClientStatistic(
            @PathVariable("clientId") String clientId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {

        return statisticManager.getClientStatisticGroupedByCategories(
                clientId,
                Instant.parse(startDate),
                Instant.parse(endDate)
        );
    }

}
