package com.ezbid.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expirationMs}")
    private long jwtExpirationMs;

    // This method extracts the username from a token
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // This method extracts a claim from a token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // This method generates a token for a user
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    // This method generates a token for a user with extra claims
    public String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails){
        return buildToken(extraClaims, userDetails, jwtExpirationMs);
    }

    // This method returns the expiration time of a token
    public long getJwtExpirationMs(){
        return jwtExpirationMs;
    }

    // This method builds a token with the information provided
    private String buildToken(HashMap<String, Object> extraClaims, UserDetails userDetails, long expirationMs){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
                .signWith(getSightInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // This method checks if a token is valid
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // This method checks if a token is expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    // This method extracts the expiration date from a token
    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // This method extracts all claims from a token
    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSightInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // This method returns the secret key (used for signing and verifying tokens)
    private Key getSightInKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
