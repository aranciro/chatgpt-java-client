package com.github.aranciro.client.pojo.rest.response;

import com.squareup.moshi.Json;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class Content implements Serializable {
    
    @Json(name = "content_type")
    private String contentType;
    @Json(name = "parts")
    private List<String> parts;
    private static final long serialVersionUID = 2605088351856185143L;
    
}
