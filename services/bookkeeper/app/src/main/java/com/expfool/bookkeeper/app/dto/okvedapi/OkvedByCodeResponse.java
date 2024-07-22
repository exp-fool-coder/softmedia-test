package com.expfool.bookkeeper.app.dto.okvedapi;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class OkvedByCodeResponse implements Serializable {

    private List<Suggestion> suggestions;

}

