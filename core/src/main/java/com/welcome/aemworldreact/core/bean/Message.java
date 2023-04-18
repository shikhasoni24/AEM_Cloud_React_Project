package com.welcome.aemworldreact.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true )
public class Message {
    private String role;
    private String content;

}
