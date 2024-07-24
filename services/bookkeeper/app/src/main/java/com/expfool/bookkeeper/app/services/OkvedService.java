package com.expfool.bookkeeper.app.services;

import com.expfool.bookkeeper.app.dto.okvedapi.OkvedByCodeRequest;
import com.expfool.bookkeeper.app.dto.okvedapi.OkvedByCodeResponse;
import com.expfool.bookkeeper.app.dto.okvedapi.Suggestion;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OkvedService {

    private final RestTemplate restTemplate;

    public OkvedService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cacheable(value = "okvedCode", key = "#code")
    public String getOkvedCategoryByCode(String code) throws Exception {
        String apiUrl = "http://suggestions.dadata.ru/suggestions/api/4_1/rs/findById/okved2";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Token 907589479a5339007dca9a199d7eb4629fb3d70d");
        HttpEntity<OkvedByCodeRequest> request = new HttpEntity<>(new OkvedByCodeRequest(code), headers);
        OkvedByCodeResponse response = restTemplate.postForObject(apiUrl, request, OkvedByCodeResponse.class);
        if (response == null) {
            //ToDo: something bad happened need to log it
            throw new Exception();
        }
        Suggestion firstSuggestion = response.getSuggestions().getFirst();

        return firstSuggestion.getData().getRazdel();
    }
}
