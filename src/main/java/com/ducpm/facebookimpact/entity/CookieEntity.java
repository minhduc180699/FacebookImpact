package com.ducpm.facebookimpact.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class CookieEntity {
    private  String name;
    private  String value;
    private  String path;
    private  String domain;
    private  Date expiry;
    private  String sameSite;
}
