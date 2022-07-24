package com.rsh.JwtTokenTest05.JwtTokenSample.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rsh.JwtTokenTest05.JwtTokenSample.domain.Role;
import com.rsh.JwtTokenTest05.JwtTokenSample.domain.User;
import com.rsh.JwtTokenTest05.JwtTokenSample.repository.RoleRepository;
import com.rsh.JwtTokenTest05.JwtTokenSample.repository.UserRepository;
import com.rsh.JwtTokenTest05.JwtTokenSample.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User saveUser(User user) {
		log.info("Saving new user {} to the database", user.getName());
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving new role {} to the database ", role.getName());
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}  ", roleName, username);
		User user = userRepository.findByUsername(username);
		Role role = roleRepository.findByName(roleName);
		user.getRoles().add(role);
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {} ", username);
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching users");
		return userRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user == null) {
			//log
			throw new UsernameNotFoundException("User not found in the database");
		} else {
			//log
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		});
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

}
