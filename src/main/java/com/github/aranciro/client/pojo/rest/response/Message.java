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
public final class Message implements Serializable {
    
    @Json(name = "id")
    private String id;
    @Json(name = "author")
    private Author author;
    @Json(name = "create_time")
    private Object createTime;
    @Json(name = "update_time")
    private Object updateTime;
    @Json(name = "content")
    private Content content;
    @Json(name = "end_turn")
    private boolean endTurn;
    @Json(name = "weight")
    private int weight;
    @Json(name = "metadata")
    private MessageMetadata metadata;
    @Json(name = "recipient")
    private String recipient;
    private static final long serialVersionUID = -6558862627944813214L;
    
}
