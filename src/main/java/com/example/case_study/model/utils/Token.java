package com.example.case_study.model.utils;

import java.time.LocalDateTime;

public class Token {
    private String value;
    private LocalDateTime expireDuration;

    public Token() {
    }

    public Token(String value, LocalDateTime expireDuration) {
        this.value = value;
        this.expireDuration = expireDuration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public LocalDateTime getExpireDuration() {
        return expireDuration;
    }

    public void setExpireDuration(LocalDateTime expireDuration) {
        this.expireDuration = expireDuration;
    }
}
