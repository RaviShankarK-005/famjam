package com.famjam.jwtUtil.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private String SECRET_KEY = "4b7b1de04582ef5d6ff7a1ad9cbac728fc0f642e0307ee933098db0c8c5d4d886e4213c92f8f3994251a096a5c08c2e00390ec16ed62e26f9b6b73c742cc1d69f65b7baf8469144bf0aa39e05023491f114c81818a84fe0db80fd2aed2e3437123b7ec08b7fc131290b9ae0bd835cb06d3cf65c32b71c98b943293867ae51ccb6504303a80061270c8f9dd15b70b63dbb4372f725bce3f17295f2a393fc95ae041b7c2901c9cb3bbd3f4dae09ce2ed84ba6f2e43d8c34a5c909c8ad1af53ed300b76a122902cb91ddc4588423975750399b1aa825d0ecf704b4d7633f0ea52525ae2e1736dd96efd57410750e9ef92d24717da61ea63511f38468ebc7ae87ab9";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                   .setClaims(claims)
                   .setSubject(subject)
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                   .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                   .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
