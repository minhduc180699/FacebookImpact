package com.ducpm.facebookimpact;

import com.ducpm.facebookimpact.GlobalConstant;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@PropertySource("file:../conf/configuration.conf")
public class AppConfig {
    @Autowired
    private Environment env;
    @PostConstruct
    private void loadConfig() {
        GlobalConstant.driverPath = env.getProperty("driverPath");
        GlobalConstant.browserPath = env.getProperty("browserPath");
        GlobalConstant.cookiesPath = env.getProperty("cookiesPath");
        GlobalConstant.detectCheckPointXPath = env.getProperty("detectCheckPointXPath");
        GlobalConstant.detectLoggedXpath = env.getProperty("detectLoggedXpath");
    }
}
