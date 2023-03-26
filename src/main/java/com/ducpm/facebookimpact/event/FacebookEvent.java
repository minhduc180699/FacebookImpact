package com.ducpm.facebookimpact.event;

import com.ducpm.facebookimpact.entity.FacebookEventEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
@Getter
@Setter
public class FacebookEvent extends ApplicationEvent {
    private FacebookEventEntity facebookEventEntity;
    public FacebookEvent(Object source, FacebookEventEntity facebookEventEntity1) {
        super(source);
        this.facebookEventEntity = facebookEventEntity1;
    }
    public FacebookEvent(Object source, Clock clock) {
        super(source, clock);
    }
}
