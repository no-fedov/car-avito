package com.javaacademy.car_avito.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestParameterUtil {

    public static String getParametersAsString(Map<String, String> parameters) {
        StringBuilder parametersAsString = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            parametersAsString.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        parametersAsString.deleteCharAt(parametersAsString.length() -1);
        return parametersAsString.toString();
    }
}
