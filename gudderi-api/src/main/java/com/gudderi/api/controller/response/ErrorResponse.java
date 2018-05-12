package com.gudderi.api.controller.response;

import org.eclipse.collections.impl.factory.Lists;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {

    public static ErrorResponse instanceOf(String userMessage) {
        return ErrorResponse.builder()
                .userMessage(userMessage)
                .build();
    }

    public static ErrorResponse instanceOf(String userMessage, BindingResult bindingResult) {
        List<ErrorDetail> objectErrorList = bindingResult.getGlobalErrors().stream().map(error ->
                ErrorDetail.instanceOf(error.getObjectName(), error.getDefaultMessage())
        ).collect(Collectors.toList());
        List<ErrorDetail> fieldErrorList = bindingResult.getFieldErrors().stream().map(fieldError ->
                ErrorDetail.instanceOf(fieldError.getField(), fieldError.getDefaultMessage())
        ).collect(Collectors.toList());

        return ErrorResponse.builder()
                .userMessage(userMessage)
                .details(Lists.mutable.withAll(objectErrorList).withAll(fieldErrorList))
                .build();
    }

    public static ErrorResponse instanceOf(String userMessage, String detailTarget, String detailMessage) {
        return ErrorResponse.builder()
                .userMessage(userMessage)
                .details(
                        Lists.mutable.of(ErrorDetail.builder()
                                .target(detailTarget)
                                .userMessage(detailMessage)
                                .build()
                        )
                )
                .build();
    }

    /**
     * ユーザー向けエラーメッセージ
     */
    private String userMessage;

    /**
     * エラーの詳細情報
     */
    private List<ErrorDetail> details;


    @Data
    @Builder
    public static class ErrorDetail {
        private String target;
        private String userMessage;

        public static ErrorDetail instanceOf(String target, String message) {
            return new ErrorDetail(target, message);
        }
    }
}
