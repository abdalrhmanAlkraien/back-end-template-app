package com.marinabits.energietechs.api.model.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record CommonResponse (

    @Schema(name = "message", example = "response message")
    String message,
    @Schema(name = "status", example = "201", description = "Response status")
    HttpStatus status,
    @Schema(name = "data", example = "completed success")
    Object data
){}
