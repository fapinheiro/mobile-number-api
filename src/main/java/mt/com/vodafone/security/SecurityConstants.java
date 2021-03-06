package mt.com.vodafone.security;

public class SecurityConstants {

	public static final String 	SECRET = "SecretKeyToGenJWTs";
    public static final int 	EXPIRATION_TIME = 1; // in hours
    public static final String 	TOKEN_PREFIX = "Bearer ";
    public static final String 	HEADER_STRING = "Authorization";
    public static final String 	SIGN_UP_URL = "/api/users/sign-up";
    public static final String 	LOGIN_URL = "/api/login";
    
}
