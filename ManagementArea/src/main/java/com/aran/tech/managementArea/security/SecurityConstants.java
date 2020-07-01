package com.aran.tech.managementArea.security;

/**
 * @author oawon
 */
public class SecurityConstants {
    
    public static final String SIGN_UP_URLS = "/api/users/**";
    public static final String H2_URL = "h2-console/**";
    public static final String SECRET ="SecretKeyToGenJWTs";
    public static final String TOKEN_PREFIX= "OngoNgonG-" ; //"Bearer ";
    public static final String HEADER_STRING = "Authorization";
    
    public static long EXPIRATION_TIME = 0*60*60*1000 ; //milliseconds
    
}
