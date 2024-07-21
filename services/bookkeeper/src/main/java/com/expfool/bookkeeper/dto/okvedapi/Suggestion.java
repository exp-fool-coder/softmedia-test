package com.expfool.bookkeeper.dto.okvedapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class Suggestion implements Serializable {
    private String value;
    private String unrestricted_value;
    private SuggestionData data;

}

