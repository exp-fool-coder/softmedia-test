package com.expfool.bookkeeper.app.dto.okvedapi;

import lombok.Data;

import java.io.Serializable;

@Data
public class OkvedByCodeRequest implements Serializable {
    private String query;

    public OkvedByCodeRequest(String query) {
        this.query = query;
    }
}
