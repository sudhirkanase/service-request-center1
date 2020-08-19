package com.wellsfargo.srca.auth.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.srca.auth.beans.AuthToken;
import com.wellsfargo.srca.auth.beans.UserLoginDetails;
import com.wellsfargo.srca.auth.cache.LoggedInUserInfoCache;
import com.wellsfargo.srca.services.AppUserDetailsService;

/**
 * 
 * Class is responsible for authentication and issuing the csrf token after
 * login success and placing the key and userdetails in cache for next processes
 * 
 * @param password
 */
@RestController
@RequestMapping("/")
public class AuthenticationController {

	@Autowired
	AppUserDetailsService userDetailservice;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	LoggedInUserInfoCache userCache;

	@PostMapping("authenticate")
	public @ResponseBody ResponseEntity<UserLoginDetails> getLogin(HttpServletRequest request, HttpServletResponse response,
			@RequestBody UserLoginDetails user) {

		authenticate(user.getUsername(), user.getPassword());
		String Id=request.getSession().getId();
//		HttpSessionCsrfTokenRepository csrfRepo = new HttpSessionCsrfTokenRepository();
//		CsrfToken token = csrfRepo.generateToken(request);

		final UserDetails userDetails = userDetailservice.loadUserByUsername(user.getUsername());

		// add in the cache
		//userCache.put(token.getToken(), (User) userDetails);
		
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

		BeanUtils.copyProperties(userDetails, user);
		user.setFullName(userDetails.getUsername());
		user.setAuthToken(Id);
		user.setPassword(null);
		return ResponseEntity.ok(user);
	}

	private void authenticate(String username, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (BadCredentialsException e) {
			throw new RuntimeException("INVALID_CREDENTIALS", e);
		} catch (Exception e) {
			throw new RuntimeException("LOGIN FAILED", e);
		}
	}

}
