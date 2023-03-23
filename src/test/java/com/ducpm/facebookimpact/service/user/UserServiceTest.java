package com.ducpm.facebookimpact.service.user;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void login() throws URISyntaxException, IOException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        String username = "minhduc180699@yandex.com";
        String password = "Pmd99916081@";
        String url = "https://www.facebook.com/";
        userService.login(url, username, password);
    }
    @Test
    public void loginUseCookie() throws URISyntaxException, IOException {
//        Cookie cookie = new
//        userService.login(url, username, password);
    }
}