package com.ducpm.facebookimpact.service.user;

import com.ducpm.facebookimpact.entity.CookieEntity;
import com.ducpm.facebookimpact.mapper.CookiesMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class UserServiceTest {
    @Autowired
    private LoginService loginService;
    @Autowired
    private Gson gson;
    @Test
    public void login() throws URISyntaxException, IOException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        String username = "minhduc180699@yandex.com";
        String password = "Pmd99916081@";
        String url = "https://www.facebook.com/";
        loginService.login(url, username, password);
    }
    @Test
    public void loginUseCookie() throws URISyntaxException, IOException {
        Path path = Paths.get("./cookies/minhduc180699");
        String url = "https://www.facebook.com/";
        String s = Files.readString(path);
        List<CookieEntity> cookieEntities = gson.fromJson(s, new TypeToken<List<CookieEntity>> (){}.getType());
        Set<Cookie> cookieSet = CookiesMapper.getCookiesFromJson(cookieEntities);
        loginService.login(url, cookieSet);
    }
}