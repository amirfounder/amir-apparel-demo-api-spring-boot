package com.amirfounder.amirappareldemoapispringboot.auth;

import org.springframework.stereotype.Service;

@Service
public class JwtUtil {

    public String parseJwtFromHeader(String bearerToken) {
        return bearerToken != null && bearerToken.startsWith("Bearer ")
            ? bearerToken.substring(7)
            : null;
    }
}
