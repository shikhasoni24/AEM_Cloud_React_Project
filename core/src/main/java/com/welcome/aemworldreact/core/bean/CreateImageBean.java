package com.welcome.aemworldreact.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateImageBean {

    String prompt;

    int n;

    String  size;

    public CreateImageBean(String prompt, int n, String size) {
        this.prompt = prompt;
        this.n = n;
        this.size = size;
    }
}
