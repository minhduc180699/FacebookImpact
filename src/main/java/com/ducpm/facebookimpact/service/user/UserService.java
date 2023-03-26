package com.ducpm.facebookimpact.service.user;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.GlobalConstant;
import com.ducpm.facebookimpact.AppConfig;
import com.ducpm.facebookimpact.entity.CookieEntity;
import com.ducpm.facebookimpact.mapper.CookiesMapper;
import com.ducpm.facebookimpact.service.Detector;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.openqa.selenium.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private Detector detector;
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
                Path path = Paths.get(GlobalConstant.cookiesPath + "/" + username);
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
        File[] files = new File(GlobalConstant.cookiesPath).listFiles();
        if (files == null) {
            return fileList;
        }
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file.getName());
            }
        }
        return fileList;
    }
}
