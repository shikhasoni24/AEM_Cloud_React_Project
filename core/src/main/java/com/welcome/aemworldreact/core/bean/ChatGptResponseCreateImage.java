package com.welcome.aemworldreact.core.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatGptResponseCreateImage {

    private List<Url> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true )
    public static class Url {
        private String url;

    }


}
