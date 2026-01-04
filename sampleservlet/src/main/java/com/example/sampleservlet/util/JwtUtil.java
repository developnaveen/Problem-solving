package com.example.sampleservlet.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor("my-secret-key".getBytes());
    private static final long EXPIRATION_TIME = 60 * 60 * 1000;

    public static String generateToken(String username){
        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SECRET_KEY, SignatureAlgorithm.ES256).compact();
    }

    public static String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public static boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }

    private static Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }

    public static boolean validateToken(String token, String username){
        return extractUsername(token).equals(username) && ! isTokenExpired(token);
    }

}
