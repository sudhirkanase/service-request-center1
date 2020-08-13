//package com.wellsfargo.srca.auth.config;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.wellsfargo.srca.auth.cache.LoggedInUserInfoCache;
//import com.wellsfargo.srca.services.AppUserDetailsService;
//
//@Component
//public class AuthenticationFilter extends OncePerRequestFilter {
//
//	@Autowired
//	AppUserDetailsService userDetailService;
//
//	@Autowired
//	LoggedInUserInfoCache userCahce;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		System.out.println("Filtering request from Auth filter");
//
//		String headerCsrfToken = (String) request.getHeader("X-CSRF-TOKEN");
//
//		// String requestURI = request.getRequestURI();
//		if (!StringUtils.isEmpty(headerCsrfToken)) {
//
//			User user = userCahce.get(headerCsrfToken);
//			if(user == null) {
//				throw new AuthenticationServiceException("Invalid token or User is sessioned out..");
//			}
//			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//					user, null, user.getAuthorities());
//			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//			// Validate the token in session
//
////			HttpSession session = request.getSession(false);
////			Object attribute = session.getAttribute("user");
////			UserDetails userDetails = this.userDetailService.loadUserByUsername("admin");
////			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////					userDetails, null, userDetails.getAuthorities());
////			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		}
//		super.doFilter(request, response, filterChain);
//		// }
//
//	}
//
//}
