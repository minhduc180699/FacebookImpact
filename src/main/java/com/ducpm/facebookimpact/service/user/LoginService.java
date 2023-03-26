package com.ducpm.facebookimpact.service.user;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.GlobalConstant;
import com.ducpm.facebookimpact.AppConfig;
import com.ducpm.facebookimpact.entity.CookieEntity;
import com.ducpm.facebookimpact.service.Detector;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class LoginService {
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private Detector detector;
    @Autowired
    private Gson gson;
    @Autowired
    private ChromeDriver chromeDriver;
    public int login(String url, String username, String password)  {
        try {
            chromeDriver.get(url);
            if (detector.detectLogged(chromeDriver)) {
                return ErrorCode.SUCCESS;
            }
            chromeDriver.findElement(By.id("email")).sendKeys(username);
            chromeDriver.findElement(By.id("pass")).sendKeys(password);
            chromeDriver.findElement(By.name("login")).click();
            chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            if (!detector.detectLogged(chromeDriver)) {
                return ErrorCode.LOGIN_USER_PASS_FAILED;
            }
            Set<Cookie> cookieSet = chromeDriver.manage().getCookies();
            JsonArray jsonArray = new JsonArray();
            List<CookieEntity> list = new LinkedList<>();
            for (Cookie cookie : cookieSet) {
                CookieEntity cookieEntity = new CookieEntity();
                cookieEntity.setDomain(cookie.getDomain());
                cookieEntity.setValue(cookie.getValue());
                cookieEntity.setName(cookie.getName());
                cookieEntity.setPath(cookie.getPath());
                cookieEntity.setSameSite(cookie.getSameSite());
                cookieEntity.setExpiry(cookie.getExpiry());
                list.add(cookieEntity);
            }
            Path path = Paths.get(GlobalConstant.cookiesPath + "/" + username);
            Files.writeString(path, gson.toJson(list), StandardOpenOption.CREATE);
//        chromeDriver.close();
            return ErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.LOGIN_USER_PASS_FAILED;
        }
    }
    public int login(String url, Set<Cookie> cookies) {
        try {
            chromeDriver.navigate().to(url);
            for (Cookie cookie : cookies) {
                chromeDriver.manage().addCookie(cookie);
            }
            chromeDriver.get(url);
            chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            if (!detector.detectLogged(chromeDriver)) {
                return ErrorCode.LOGIN_COOKIE_FAILED;
            }
            return ErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.LOGIN_COOKIE_FAILED;
        }
    }
}
