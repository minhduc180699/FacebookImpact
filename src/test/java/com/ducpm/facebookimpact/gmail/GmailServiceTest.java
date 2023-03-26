package com.ducpm.facebookimpact.gmail;

import com.ducpm.facebookimpact.entity.Car;
import com.ducpm.facebookimpact.entity.Chuck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GmailServiceTest {
//    @Autowired
//    private GmailService gmailService;
//    @Test
//    public void sendMail() {
//        gmailService.sendEmail();
//    }
    @Test
    private void test() {
        Chuck chuck = new Chuck();
        run(chuck);
    }
    private static void run(Car car) {
        if (car instanceof Chuck) {
            System.out.println(111111);
        }
    }
}