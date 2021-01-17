package com.transaction.service.impl;

import static com.transaction.config.SpringSecurityConfig.listUser;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> userFouded = listUser.stream().filter(user -> user.getUsername().equals(userName)).findFirst();
		if (userFouded.isPresent()) {
			return new User(userName, userFouded.get().getPassword(), new ArrayList<>());
		}
		throw new UsernameNotFoundException("User not found");
	}
}