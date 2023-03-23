package com.ducpm.facebookimpact.service.impact;

import com.ducpm.facebookimpact.ErrorCode;
import com.ducpm.facebookimpact.schedule.LikeSchedule;
import com.google.gson.Gson;
import jakarta.annotation.PreDestroy;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LikeService {
    private Logger logger = LoggerFactory.getLogger(LikeService.class);
    @Autowired
    private Gson gson;
    @Autowired
    private ChromeDriver chromeDriver;
    @PreDestroy
    public void onExit() {
        try {
            chromeDriver.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int likeFeedLink(String url)  {
        try {
            int count = 2;
            chromeDriver.get(url);
            while (count > 0) {
                List<WebElement> elements = chromeDriver.findElements(By.xpath("//div[@aria-label='Thích']"));
                TimeUnit.MILLISECONDS.sleep(4000);
                if (elements == null || elements.isEmpty()) {
                    chromeDriver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
                    logger.info("[PAGE DOWN] SUCCESS");
                } else {
                    for (WebElement element : elements) {
                        if (!LikeUtils.isLike(element)) {
                            new Actions(chromeDriver).moveToElement(element).perform();
                            TimeUnit.MILLISECONDS.sleep(4000);
//                            chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(2));
                            element.click();
                            logger.info("[LIKE] SUCCESS");
                        }
                    }
                }
                count --;
            }
            return ErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.LIKE_FEED_LINK_FAILED;
        }
    }
    public int likePersonalPost(List<String> urls)  {
        try {
            String originalWindow = chromeDriver.getWindowHandle();
            for (String url : urls) {
                int numLike = 0;
                long startTime = System.currentTimeMillis();
//                TimeUnit.MILLISECONDS.sleep(1000);
                chromeDriver.switchTo().newWindow(WindowType.WINDOW);
                chromeDriver.navigate().to(url);
                chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(7));
                while (numLike < 3 && (System.currentTimeMillis() - startTime) < 18000) {
//                    TimeUnit.MILLISECONDS.sleep(1000);
                    List<WebElement> elements = new LinkedList<>();
                    try {
                        List<WebElement> elementsList = chromeDriver.findElements(By.xpath("//div[@aria-label='Thích']"));
                        for (WebElement element : elementsList) {
                            List<WebElement> l1 = element.findElements(By.tagName("div"));
                            if ( l1.size() > 1) {
                                elements.add(element);
                            }
                        }
                    } catch (Exception e){

                    }
                    if (elements == null || elements.isEmpty()) {
                        chromeDriver.findElement(By.cssSelector("body")).sendKeys(Keys.PAGE_DOWN);
                        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
                        TimeUnit.MILLISECONDS.sleep(4000);
                        logger.info("[PAGE DOWN] SUCCESS url: {}", url);
                    } else {
                        for (WebElement element : elements) {
                            if (!LikeUtils.isLike(element)) {
                                new Actions(chromeDriver).moveToElement(element).perform();
                                element.click();
                                TimeUnit.MILLISECONDS.sleep(4000);
//                                chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1));
                                numLike ++;
                                logger.info("[LIKE] SUCCESS url: {}", url);
                                if (numLike > 2) break;
                            }
                        }
                    }
                }
                chromeDriver.close();
                chromeDriver.switchTo().window(originalWindow);
            }
            return ErrorCode.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ErrorCode.LIKE_FEED_LINK_FAILED;
        }
    }
}
