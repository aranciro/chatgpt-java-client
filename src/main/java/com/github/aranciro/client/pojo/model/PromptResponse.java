package com.github.aranciro.client.pojo.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class PromptResponse {
    
    @NonNull
    private final String text;
    private final boolean violatesPolicy;
    
}
