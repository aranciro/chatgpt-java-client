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
public final class FinishDetails implements Serializable {
    
    @Json(name = "type")
    private String type;
    @Json(name = "stop")
    private String stop;
    private static final long serialVersionUID = -2609270168953157707L;
    
}
