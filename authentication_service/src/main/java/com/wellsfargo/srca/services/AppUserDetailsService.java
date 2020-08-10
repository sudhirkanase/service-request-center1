package com.wellsfargo.srca.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wellsfargo.srca.auth.dao.UserRepository;
import com.wellsfargo.srca.auth.entity.User;

@Service
public class AppUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired//(required = false)
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		User userEntity = userRepository.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Email " + userName + " not found"));
		return new org.springframework.security.core.userdetails.User(userEntity.getUserName(), userEntity.getPassword(),
				getAuthorities(userEntity));
	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
