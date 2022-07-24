package com.rsh.JwtTokenTest05.JwtTokenSample.service;

import java.util.List;

import com.rsh.JwtTokenTest05.JwtTokenSample.domain.Role;
import com.rsh.JwtTokenTest05.JwtTokenSample.domain.User;

public interface UserService {
	
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username, String roleName);
	User getUser(String username);
	List<User> getUsers();

}
