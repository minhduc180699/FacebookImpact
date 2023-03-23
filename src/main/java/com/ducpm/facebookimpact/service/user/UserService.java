package com.ducpm.facebookimpact.service.user;

import com.ducpm.facebookimpact.browser.Browser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private LoginService loginService;
    @Autowired
    private LogoutService logoutService;
    @Autowired
    private ChromeDriver chromeDriver;
    public int login(String url, String username, String password) throws URISyntaxException, IOException {
        chromeDriver.get(url);
        chromeDriver.findElement(By.id("email")).sendKeys(username);
        chromeDriver.findElement(By.id("pass")).sendKeys(password);
        chromeDriver.findElement(By.name("login")).click();
        chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Set<Cookie> cookieSet = chromeDriver.manage().getCookies();
        JsonArray jsonArray = new JsonArray();
        List<JsonObject> list = new LinkedList<>();
        for (Cookie cookie : cookieSet) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("domain", cookie.getDomain());
            jsonObject.addProperty("value", cookie.getValue());
            jsonObject.addProperty("name", cookie.getName());
            jsonObject.addProperty("path", cookie.getPath());
            jsonObject.addProperty("samesite", cookie.getSameSite());
            jsonObject.addProperty("expiry", cookie.getExpiry().toString());
            list.add(jsonObject);
        }
        Path path = Paths.get("./cookies/minhduc180699.txt");
        Files.writeString(path, list.toString());
//        chromeDriver.close();
        return 0;
    }
    public int login(String url, Set<Cookie> cookies) throws URISyntaxException, IOException {
        for (Cookie cookie : cookies) {
            chromeDriver.manage().addCookie(cookie);
        }
        chromeDriver.get(url);
        return 0;
    }
}
