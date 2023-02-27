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
public final class MessageMetadata implements Serializable {
    
    @Json(name = "message_type")
    private String messageType;
    @Json(name = "model_slug")
    private String modelSlug;
    @Json(name = "finish_details")
    private FinishDetails finishDetails;
    private static final long serialVersionUID = 2210244884562645430L;
    
}
