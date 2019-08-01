package br.com.devdojo.springboot.security.filter;

public class SecurityConstants {
	
	public static final String SECRET = "secre";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final long EXPIRATION_TIME = 86400000; //1 dia
	
	
	
	
	
	/*
	  public static void main(String[] args) {
	  System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
	  System.out.println(new BCryptPasswordEncoder().encode("f@p3p12o1B")); }
	 
	  */
	 
	 
}
