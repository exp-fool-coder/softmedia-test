package com.expfool.statz.app.dto;

import java.util.List;

public record CategoryData(
        String okvedCategory,
        int amount,
        String formattedAmount,
        List<Payment> payments
) {}
