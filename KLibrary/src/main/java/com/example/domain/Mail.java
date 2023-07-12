package com.example.domain;

import lombok.Data;
import org.springframework.context.annotation.Bean;

@Data
public class Mail {
    private String from;
    private String to;
    private String subject;
    private String context;
    private String bookUrl;
}
