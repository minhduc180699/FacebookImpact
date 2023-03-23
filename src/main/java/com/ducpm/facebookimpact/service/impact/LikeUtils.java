package com.ducpm.facebookimpact.service.impact;

import org.openqa.selenium.WebElement;

public class LikeUtils {
    public static boolean isLike(WebElement element) {
        if (element.getAttribute("style").contains("color")) {
            return true;
        } else return false;
    }
}
