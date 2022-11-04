package com.revature.quizlite.service;

import com.revature.quizlite.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 *      This class is intended to Construct new tokens, validate existing tokens by extracting and validating claim
 */
@Service
public class JWTService {

    // This should be placed in a safe space
    private final String SIGNING_KEY = "super-secret";
    private final Long HOUR = (1000L * 60 * 60);
    private final Long DAY = HOUR * 24;

    public Boolean validateToken(String token, User user){
        String username = extractUsername(token); // I need to compare this to something -> the dbUser
        return (!isTokenExpired(token) && username.equals(user.getUsername()));
    }

    public String generateToken(User user){
        Map<String, Object> claims = new HashMap<>(); // load up this object with claims you want on the token
        return generateToken(claims, user.getUsername());
    }

    private String extractUsername(String token){
        return extractClaim(token, Claims::getSubject); // this is called a method reference
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        // validate that the expiration date is before NOW
        return extractExpiration(token).before(new Date());
    }

    private String generateToken(Map<String, Object> claims, String subject){
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + (HOUR * 24)))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
    }

    //   currentTimeInMillis
    //      how much do I need to add (in millis) per hour
}
