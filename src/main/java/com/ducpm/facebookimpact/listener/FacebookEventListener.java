package com.ducpm.facebookimpact.listener;

import com.ducpm.facebookimpact.event.FacebookEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FacebookEventListener implements ApplicationListener<FacebookEvent> {
    private Logger logger = LoggerFactory.getLogger(FacebookEventListener.class);
    @Override
    public void onApplicationEvent(FacebookEvent event) {
        logger.info("[FACEBOOK LISTENER] {} - code: {} - {}", event.getFacebookEventEntity().getTypeEvent(), event.getFacebookEventEntity().getCode(), event.getFacebookEventEntity().getContent());
    }
}
