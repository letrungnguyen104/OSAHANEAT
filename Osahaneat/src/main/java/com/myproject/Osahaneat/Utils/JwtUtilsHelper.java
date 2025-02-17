package com.myproject.Osahaneat.Utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;

@Component
public class JwtUtilsHelper {

    //Lấy key có trong file yml
    @Value("${jwt.privateKey}")
    private String privateKey;

    //Sinh ra token dựa trên key
    public String generateToken(String data){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
        String jws = Jwts.builder().subject(data).signWith(key).compact();
        return jws;
    }

    public boolean verifyToken(String token){
        try {
            SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
