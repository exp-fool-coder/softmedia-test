package com.expfool.statz.app.controllers;

import com.expfool.bookkeeper.api.Entities.GetClientStatisticRequest;
import com.expfool.bookkeeper.api.Services.BookkeeperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/statistic")
public class PaymentStatisticController {

    private final BookkeeperService bookkeeperService;

    public PaymentStatisticController(BookkeeperService bookkeeperService) {
        this.bookkeeperService = bookkeeperService;
    }

    @GetMapping("/client/{clientId}")
    public String getClientStatistic(@PathVariable("clientId") String clientId) {
        var response = bookkeeperService.getClientStatistic(new GetClientStatisticRequest(
            "1234",
            Instant.now(),
            Instant.now()
        ));
        return response.answer();
    }

}
