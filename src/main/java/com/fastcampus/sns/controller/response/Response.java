package com.fastcampus.sns.controller.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private String resultCode;
    private T result;


    public static <T> Response<Void> error(String errorCode){
        return new Response<>(errorCode, null);
    }

    public static <T> Response<T> success(T result){
        return new Response<>("success", result);
    }

    public String toStream() {
        if (result == null) {
            return "{" +
                    "\"resultCode\"" + resultCode + "\"" +
                    "\"result\":" +  null  + "}";
        }

        return "{" +
                "\"resultCode\"" + resultCode + "\"" +
                "\"result\":" + "\"" + result + "\"" + "}";
    }
}
