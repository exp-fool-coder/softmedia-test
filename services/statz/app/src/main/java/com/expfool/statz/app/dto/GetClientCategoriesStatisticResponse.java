package com.expfool.statz.app.dto;

import java.util.List;

public record GetClientCategoriesStatisticResponse(
        String clientId,
        List<CategoryData> data
) {}
