package com.ridex.ridex.security;

import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

    private static final String SECRET =
            "RidexSecretKeyRidexSecretKey123456";

    private static final Key KEY =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes()
            );

    // GENERATE TOKEN

    public static String generateToken(String email){

        return Jwts.builder()

                .setSubject(email)

                .setIssuedAt(
                        new Date()
                )

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                + 86400000
                        )
                )

                .signWith(
                        KEY,
                        SignatureAlgorithm.HS256
                )

                .compact();

    }

    // EXTRACT EMAIL

    public static String extractEmail(String token){

        return Jwts.parserBuilder()

                .setSigningKey(KEY)

                .build()

                .parseClaimsJws(token)

                .getBody()

                .getSubject();

    }

    // VALIDATE TOKEN

    public static boolean validateToken(String token){

        try{

            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);

            return true;

        }

        catch(JwtException e){

            return false;

        }

    }

}
