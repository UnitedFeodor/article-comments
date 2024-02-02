package com.innowise.articlecomments.dto;


import java.time.Instant;

public record ErrorMessage(
        Instant timestamp,
        String message

) {
    public ErrorMessage(String message) {
        this(Instant.now(), message);
    }

}
