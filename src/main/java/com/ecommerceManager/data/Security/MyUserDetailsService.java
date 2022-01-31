package com.ecommerceManager.data.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerceManager.data.User;
import com.ecommerceManager.data.UserPrincipal;
import com.ecommerceManager.data.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		if(user == null) { 
			throw new UsernameNotFoundException("Username not found");
			}
		
		return new UserPrincipal(user.getUsername(), user.getPassword());
			
	}
}
