package com.marinabits.energietechs.api.model;

import com.marinabits.energietechs.api.model.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Abd-alrhman Alkraien.
 * @Date: 10/5/2023
 * @Time: 11:17 PM
 */
public class ResponseHandler {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);


        return new ResponseEntity<>(CommonResponse
                .builder()
                .message(message)
                .status(status)
                .data(responseObj)
                .build(), status);
    }
}
