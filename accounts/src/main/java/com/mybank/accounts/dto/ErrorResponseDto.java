package com.mybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "Error response dto", description = "schema to hold error response information")
@Data
@AllArgsConstructor
public class ErrorResponseDto {
    @Schema(description = "api path invoke by client")
    private String apiPath;
    @Schema(description = "Error code representing the error happened")
    private HttpStatus errorCode;
    @Schema(description = "Error massage representing the error happened")
    private String errorMassage;
    @Schema(description = "Time representing when the error happens")
    private LocalDateTime errorTime;

}
