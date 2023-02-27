package com.github.aranciro.client.pojo.rest.response;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class Details implements Serializable {
    
    private static final long serialVersionUID = 713123559472518778L;
    @Json(name = "message")
    private Message message;
    @Json(name = "conversation_id")
    private String conversationId;
    @Json(name = "error")
    private Object error;
    
}
