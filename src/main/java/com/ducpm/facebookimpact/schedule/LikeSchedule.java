package com.ducpm.facebookimpact.schedule;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.personal.PersonalList;
import com.ducpm.facebookimpact.service.impact.LikeService;
import com.ducpm.facebookimpact.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

@Configuration
@EnableScheduling
public class LikeSchedule {
    private Logger logger = LoggerFactory.getLogger(LikeSchedule.class);
    @Autowired
    private UserService userService;
    @Autowired
    private LikeService likeService;
    @Scheduled(fixedDelay = 120000)
    public void like() {
        String username = "minhduc180699@yandex.com";
        String password = "Pmd99916081@";
        String url = "https://www.facebook.com/";
        logger.error("[SCHEDULER] START ");
        int code = userService.login(url, username, password);
        if (code != ErrorCode.SUCCESS) {
            logger.error("[LOGIN] FAILED with code {}", code);
            return;
        }
        logger.info("[LOGIN] SUCCESS with code {}", code);
        int likeCode = likeService.likePersonalPost(Arrays.asList(PersonalList.urls));
        if (likeCode != ErrorCode.SUCCESS) {
            logger.error("[LIKE] FAILED with code {}", code);
            return;
        }
        logger.info("[LIKE] SUCCESS with code {}", code);

    }
}
