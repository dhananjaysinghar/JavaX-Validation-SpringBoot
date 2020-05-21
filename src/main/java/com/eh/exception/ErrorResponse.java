package com.eh.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String message;
    private String status;
    private String time = ZonedDateTime.now(ZoneId.of("Asia/Kolkata")).toString();
}
