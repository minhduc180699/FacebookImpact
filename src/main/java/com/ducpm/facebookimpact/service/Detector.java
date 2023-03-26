package com.ducpm.facebookimpact.service;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.GlobalConstant;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Detector {
    public boolean detectCheckpoint(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(GlobalConstant.detectCheckPointXPath));
        return elements != null && !elements.isEmpty();
    }
    public boolean detectLogged(WebDriver driver) {
        List<WebElement> elements = driver.findElements(By.xpath(GlobalConstant.detectLoggedXpath));
        return elements != null && !elements.isEmpty();
    }
}
