package com.github.aranciro.client.pojo.rest.response;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class ConversationResponse implements Serializable {
    
    @NotBlank
    @Json(name = "response")
    private String response;
    @NotBlank
    @Json(name = "conversationId")
    private String conversationId;
    @NotBlank
    @Json(name = "messageId")
    private String messageId;
    @Json(name = "details")
    private Details details;
    private static final long serialVersionUID = -6409588938565323710L;
    
}
