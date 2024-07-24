package com.expfool.statz.app.managers;

import com.expfool.bookkeeper.api.Entities.GetClientStatisticRequest;
import com.expfool.bookkeeper.api.Entities.GetClientStatisticResponse;
import com.expfool.bookkeeper.api.Entities.Payment;
import com.expfool.bookkeeper.api.Services.BookkeeperService;
import com.expfool.statz.app.dto.CategoryData;
import com.expfool.statz.app.dto.GetClientCategoriesStatisticResponse;
import com.expfool.statz.app.utils.MoneyFormatterUtils;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticManager {

    private final BookkeeperService bookkeeperService;

    public StatisticManager(BookkeeperService bookkeeperService) {
        this.bookkeeperService = bookkeeperService;
    }

    public GetClientCategoriesStatisticResponse getClientStatisticGroupedByCategories(
            String clientId,
            Instant startDate,
            Instant endDate
    ) {
        var request = new GetClientStatisticRequest(clientId, startDate, endDate);
        GetClientStatisticResponse response = bookkeeperService.getClientStatistic(request);

        Map<String, List<Payment>> paymentsGroupByCategories =
                response.payments().stream().collect(Collectors.groupingBy(Payment::okvedCategory));

        List<CategoryData> categories = paymentsGroupByCategories.entrySet().stream().map(payments -> {
            var apiPayments =
                    payments.getValue().stream().map(com.expfool.statz.app.dto.Payment::new).toList();
            int amountSum = apiPayments.stream().mapToInt(com.expfool.statz.app.dto.Payment::amount).sum();
            return new CategoryData(
                payments.getKey(),
                amountSum,
                MoneyFormatterUtils.formatDefaultRubble(amountSum),
                apiPayments
            );
        }).toList();

        return new GetClientCategoriesStatisticResponse(
            clientId,
            categories
        );
    }
}
