package com.ducpm.facebookimpact.browser;

import com.ducpm.facebookimpact.GlobalConstant;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Chrome{
    @Bean
    public ChromeDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", GlobalConstant.driverPath);
        ChromeOptions options = new ChromeOptions();
        options.setBinary(GlobalConstant.browserPath);
//        options.addArguments("--headless");
//        options.addArguments("--start-fullscreen");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("user-agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36\"");
        return new ChromeDriver(options);
    }
//    @Bean
//    public ChromeOptions chromeOptions() {
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary(GlobalConstant.browserPath);
////        options.addArguments("--headless");
//        options.addArguments("--start-fullscreen");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("user-agent=\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36\"");
//        return options;
//    }
}
