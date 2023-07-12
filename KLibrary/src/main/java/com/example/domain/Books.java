package com.example.domain;

import lombok.Data;

@Data
public class Books {
    private Integer id;
    private String name;
    private String type;
    private String description;
    private String url;
}
