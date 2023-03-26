package com.ducpm.facebookimpact.entity;

import com.ducpm.facebookimpact.enums.TypeEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacebookEventEntity {

    private TypeEvent typeEvent;
    private int code = 0;

    public FacebookEventEntity(TypeEvent typeEvent, int code, String content) {
        this.typeEvent = typeEvent;
        this.code = code;
        this.content = content;
    }

    private String content;
    public FacebookEventEntity(int code) {
        this.code = code;
    }
}
