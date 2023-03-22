package com.ducpm.facebookimpact.service.user;

import org.springframework.stereotype.Service;

@Service
public class LoginService extends UserService{

    @Override
    public int login(String bearerToken) {
        return 0;
    }
}
