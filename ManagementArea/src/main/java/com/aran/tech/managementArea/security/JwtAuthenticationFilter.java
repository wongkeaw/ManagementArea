package com.aran.tech.managementArea.security;

import static com.aran.tech.managementArea.security.SecurityConstants.HEADER_STRING;
import static com.aran.tech.managementArea.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.aran.tech.managementArea.domain.SessionWeb;
import com.aran.tech.managementArea.domain.User;
import com.aran.tech.managementArea.services.CustomUserDetailsService;
import com.aran.tech.managementArea.services.SessionWebService;
/**
 * @author oawon
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Autowired
    private SessionWebService sessionService ;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {

            String jwt = getJWTFromRequest(httpServletRequest);

            if(StringUtils.hasText(jwt)&& tokenProvider.validateToken(jwt)){
                Long userId = tokenProvider.getUserIdFromJWT(jwt);
                User userDetails = customUserDetailsService.loadUserById(userId);
                if (validateWithDB(userId, jwt)) {
                	// is good
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( userDetails, null, Collections.emptyList());
	
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
	                SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                	new SignatureException("Invalid JWT Signature") ;
                }
            }else {
            	System.out.println("validateToken is false");
            }

        }catch (Exception ex){
            logger.error("Could not set user authentication in security context", ex);
        }


        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
    
    private boolean validateWithDB(Long userId , String jwt ) {
    	SessionWeb sess =  sessionService.getById(userId) ;
    	String authorization = "" ;
    	if (sess == null) {
        	return false ;
        } else {
        	authorization = sess.getAuthorization() ;
        	authorization = authorization.substring(TOKEN_PREFIX.length(), sess.getAuthorization().length());
        }
    	
    	if ( jwt.equals(authorization) ) {
    		return true ;
    	}
    	return false ;
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(HEADER_STRING);

        if(StringUtils.hasText(bearerToken)&&bearerToken.startsWith(TOKEN_PREFIX)){
            return bearerToken.substring(TOKEN_PREFIX.length(), bearerToken.length());
        }

        return null;
    }
}
