package com.github.aranciro.client.pojo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Prompt {
    
    @NonNull
    private final String text;
    
}
