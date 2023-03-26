package com.ducpm.facebookimpact;

import jakarta.annotation.PreDestroy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FacebookImpactApplication {
    public static void main(String[] args) {
        SpringApplication.run(FacebookImpactApplication.class, args);
    }
}
