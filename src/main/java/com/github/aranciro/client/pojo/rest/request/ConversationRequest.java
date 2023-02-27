package com.github.aranciro.client.pojo.rest.request;

import com.squareup.moshi.Json;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public final class ConversationRequest implements Serializable {
    
    @Json(name = "message")
    private String prompt;
    @Json(name = "conversationId")
    private String conversationId;
    @Json(name = "parentMessageId")
    private String parentMessageId;
    
}
