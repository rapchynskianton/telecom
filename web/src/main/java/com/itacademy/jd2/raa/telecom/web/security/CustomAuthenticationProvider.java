package com.itacademy.jd2.raa.telecom.web.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.itacademy.jd2.raa.telecom.dao.api.entity.table.IUserAccount;
import com.itacademy.jd2.raa.telecom.service.IUserAccountService;
import com.itacademy.jd2.raa.telecom.service.util.GenerateMD5;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final IUserAccountService userService;

	@Autowired
	public CustomAuthenticationProvider(IUserAccountService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		final String userEmail = authentication.getPrincipal() + "";
		final String password = authentication.getCredentials() + "";

		
		IUserAccount user = userService.getUserAndEmail(userEmail);

		
		if (!user.getEmail().equals(userEmail)) {
			throw new BadCredentialsException("1000");
		}
		GenerateMD5 userPassword = new GenerateMD5();
		if (!user.getUserPassword().equals(userPassword.getMD5Password(password, (byte) 33))) {
			throw new BadCredentialsException("1000");
		}

		final int userId = user.getId();

		List<String> userRoles = new ArrayList<>();
		userRoles.add("ROLE_" + user.getRole());

		final List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (String roleName : userRoles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}

		ExtendedToken token = new ExtendedToken(userEmail, password, authorities);
		token.setId(userId);
		return token;

	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
