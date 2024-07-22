package com.expfool.bookkeeper.app.dto.okvedapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class SuggestionData implements Serializable {
    private String idx;
    private String razdel;
    private String kod;
    private String name;
}
