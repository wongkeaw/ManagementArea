package com.aran.tech.managementArea.security;

import static com.aran.tech.managementArea.security.SecurityConstants.SECRET;
import static com.aran.tech.managementArea.security.SecurityConstants.TOKEN_PREFIX;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.aran.tech.managementArea.domain.SessionWeb;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.property.ApplicationProperties;
import com.aran.tech.managementArea.services.SessionWebService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
/**
 * @author oawon
 */
@Component
public class JwtTokenProvider {
	
    @Autowired
    private SessionWebService sessionService ;

    @Autowired
    private ApplicationProperties applicationProperties ;
    
    //Generate the token
    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = null ;
        System.out.println("EXPIRATION_TIME : "+ applicationProperties.getExpirationTime());
        if (applicationProperties.getExpirationTime() > 0) 
        	expiryDate = new Date(System.currentTimeMillis()+applicationProperties.getExpirationTime());
        
        String uuid = UUID.randomUUID().toString();
        System.out.println("uuid : " + uuid);

        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());
        claims.put("uuid", uuid);

        String jwtb = TOKEN_PREFIX + Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now) 
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        SessionWeb session = new SessionWeb() ;
        session.setId(user.getId());
        session.setAuthorization(jwtb);
        sessionService.saveOrUpdateSession(session) ;
        return jwtb ;
        }

    //Validate the token
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }


    //Get user Id from token

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return Long.parseLong(id);
    }
    
    public Claims getFromJWT(String token){
        Claims claims = (Claims) Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        return claims;
    }
}
