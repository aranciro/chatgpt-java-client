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
public final class Author implements Serializable {
    
    @Json(name = "role")
    private String role;
    @Json(name = "name")
    private Object name;
    @Json(name = "metadata")
    private AuthorMetadata metadata;
    private static final long serialVersionUID = 3185013319414578269L;
    
}
