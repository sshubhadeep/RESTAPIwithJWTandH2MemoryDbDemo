package com.sshubhadep.api.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Secured
@Provider
@Priority(1)
public class JWTAuthFilter implements ContainerRequestFilter{
  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
        String authHeaderVal = requestContext.getHeaderString("Authorization");
            //consume JWT i.e. execute signature validation
            if(authHeaderVal!= null && authHeaderVal.startsWith("Bearer")){
            try {
                validate(authHeaderVal.split(" ")[1]);
            } catch (Exception ex) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
            }else{
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            }
  }

private void validate(String recievedjwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
	String jwt = recievedjwt;
	Jws<Claims> claims = Jwts.parser()
	  .setSigningKey("secret".getBytes("UTF-8"))
	  .parseClaimsJws(jwt);
	
	String issuer = (String) claims.getBody().getIssuer();
	Date exp = claims.getBody().getExpiration();
	Date date = new Date();
	if("sshubhadeep".equalsIgnoreCase(issuer) || exp.getTime() > date.getTime())
		System.out.println("User validated successfully.");	
}	
	
}