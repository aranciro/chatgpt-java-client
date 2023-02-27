package com.github.aranciro.client.pojo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Conversation {
    
    @NonNull
    private String id;
    @NonNull
    private String lastMessageId;
    
}
