package com.github.aranciro.client.pojo.rest.error;

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
public final class ServerError implements Serializable {
    
    @Json(name = "error")
    private String error;
    
}
