package com.example.FastcampusSpringBasics.day9.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

public class JwtTokenProvider {

    private String secretKey = "ssssss00000000000000000";
    private long tokenValidTime = 30* 60 * 1000L; // 30min

    private Key key;

    @Autowired
    private UserDetailsService userDetailsService;

    //    @PostConstruct
    //    public void init() {
    //        secretKey = Base64.getEncoder()
    //            .encodeToString(secretKey.getBytes());
    //    }
    @PostConstruct
    public void init() {
        //String base64EncodedSecretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        //byte[] keyBytes = Decoders.BASE64.decode(base64EncodedSecretKey);
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(String username, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + tokenValidTime))
            .signWith(key, SignatureAlgorithm.HS256)
            //.signWith(secretKey, SignatureAlgorithm.HS256)
            .compact();
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        // 토근 쪼개서, username 꺼냈어요.
        //Jwts.parserBuilder()      .requireAudience("string")      .build()      .parse(jwtString)
        return Jwts.parser().setSigningKey(secretKey)
            .parseClaimsJws(token).getBody().getSubject();
    }

}
