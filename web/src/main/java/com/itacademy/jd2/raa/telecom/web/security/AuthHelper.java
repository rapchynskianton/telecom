package com.itacademy.jd2.raa.telecom.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelper {

    private AuthHelper() {
    }

    public static Integer getLoggedUserId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        if (!(auth instanceof ExtendedToken)) {
            return null;
        }


        ExtendedToken extendedAuth = (ExtendedToken) auth;
        return extendedAuth.getId();
    }
}
