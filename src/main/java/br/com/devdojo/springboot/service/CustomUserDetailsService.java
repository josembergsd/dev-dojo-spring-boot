package br.com.devdojo.springboot.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.devdojo.springboot.persistence.model.ApplicationUser;
import br.com.devdojo.springboot.persistence.repository.ApplicationUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private ApplicationUserRepository applicationUserRespository;
	
	@Autowired
	public CustomUserDetailsService(ApplicationUserRepository applicationUserRespository) {
		super();
		this.applicationUserRespository = applicationUserRespository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ApplicationUser applicationUser =  loadApplicationUserByUsername(username);
		return new CustomUserDetails(applicationUser);
	}
	
	public ApplicationUser loadApplicationUserByUsername(String username) {
		return Optional.ofNullable(applicationUserRespository.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException("ApplicationUser not found"));
	}
	
	private final static class CustomUserDetails extends ApplicationUser implements UserDetails{
		
		private static final long serialVersionUID = 7743755245268649180L;

		private CustomUserDetails(ApplicationUser applicationUser) {
			super(applicationUser);
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			
			List<GrantedAuthority> authorityListApplicationAdmin = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
			List<GrantedAuthority> authorityListApplicationUser = AuthorityUtils.createAuthorityList("ROLE_USER");
			return this.getIsAdmin() == 1 ? authorityListApplicationAdmin : authorityListApplicationUser;
		}

		@Override
		public boolean isAccountNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return true;
		}
		
	}
	
}
