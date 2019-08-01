package br.com.devdojo.springboot.security.filter;

import static br.com.devdojo.springboot.security.filter.SecurityConstants.*;

/**
 * Class for authorization com uso de Tokens
 */

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.devdojo.springboot.persistence.model.ApplicationUser;
import br.com.devdojo.springboot.service.CustomUserDetailsService;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{

	private final CustomUserDetailsService customUserDetailsService;

	@Autowired
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailsService customUserDetailsService) {
		super(authenticationManager);
		this.customUserDetailsService = customUserDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String header = request.getHeader(HEADER_STRING);
		if(header == null || !header.startsWith(TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
		return;
	}
	
	//Lê o JWS
	private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if(token == null) return null;
		String username = Jwts.parser().setSigningKey(SECRET)
				.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
				.getBody()
				.getSubject();
		//System.out.println("Username no Jwts: " + username);
		UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
		ApplicationUser applicationUser =  customUserDetailsService.loadApplicationUserByUsername(username);
		return username != null ? 
				new UsernamePasswordAuthenticationToken(applicationUser, null, userDetails.getAuthorities()): null;
	}

}
