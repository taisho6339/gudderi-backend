package com.gudderi.api.controller.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class ArtistSearchRequest {
    @NotBlank
    private String keyword;
}
