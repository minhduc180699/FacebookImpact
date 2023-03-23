package com.ducpm.facebookimpact.service.user;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.GlobalConstant;
import com.ducpm.facebookimpact.browser.Browser;
import com.ducpm.facebookimpact.entity.CookieEntity;
import com.ducpm.facebookimpact.mapper.CookiesMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private Gson gson;
    @Autowired
    private LoginService loginService;
    @Autowired
    private LogoutService logoutService;
    public int login(String url, String username, String password)  {
        int errorCode = 0;
        try {
            List<String> fileList = getAllFileName();
            if (!fileList.contains(username)) {
                errorCode = loginService.login(url, username, password);
            } else {
                Path path = Paths.get(GlobalConstant.COOKIES_PATH + "/" + username);
                String s = Files.readString(path);
                List<CookieEntity> cookieEntities = gson.fromJson(s, new TypeToken<List<CookieEntity>>(){}.getType());
                Set<Cookie> cookieSet = CookiesMapper.getCookiesFromJson(cookieEntities);
                errorCode = loginService.login(url, cookieSet);
            }
            return errorCode;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.LOGIN_FAILED;
        }
    }
    private List<String> getAllFileName() {
        List<String> fileList = new ArrayList<String>();
        File[] files = new File(GlobalConstant.COOKIES_PATH).listFiles();
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file.getName());
            }
        }
        return fileList;
    }
}
