package com.example.schoolAccess.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionBody {
    private String timestamp;
    private String message;
}
