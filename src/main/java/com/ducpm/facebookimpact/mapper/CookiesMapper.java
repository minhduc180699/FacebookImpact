package com.ducpm.facebookimpact.mapper;

import com.ducpm.facebookimpact.entity.CookieEntity;
import com.google.gson.JsonObject;
import org.openqa.selenium.Cookie;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CookiesMapper {
    public static Set<Cookie> getCookiesFromJson(List<CookieEntity> cookieEntities) {
        Set<Cookie> cookieSet = new HashSet<>();
        for (CookieEntity cookieEntity : cookieEntities) {
            Cookie cookie = new Cookie(
                    cookieEntity.getName(),
                    cookieEntity.getValue(),
                    cookieEntity.getDomain(),
                    cookieEntity.getPath(),
                    cookieEntity.getExpiry(),
                    false,
                    false,
                    cookieEntity.getSameSite()
            );
            cookieSet.add(cookie);
        }
        return cookieSet;
    }
}
