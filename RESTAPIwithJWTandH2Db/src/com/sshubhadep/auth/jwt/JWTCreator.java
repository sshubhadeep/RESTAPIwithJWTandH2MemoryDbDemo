package com.sshubhadep.auth.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.sshubhadep.auth.user.User;

public class JWTCreator {
	
	public static String generateJWT(User user){
		
		long currentDateTime = new Date().getTime();
		Date exprirationDate = new Date(currentDateTime+(15*60*1000l));
		String jwt = "";
		try {
			jwt = Jwts.builder()
					  .setIssuer("sshubhadeep")
					  .setSubject("user jwt creation")
					  .setExpiration(exprirationDate)
					  .claim("fname", user.getFirstName())
					  .claim("lname", user.getLastName())
					  .signWith(
					    SignatureAlgorithm.HS256,"secret".getBytes("UTF-8")
					  )
					  .compact();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jwt;
	}
}
