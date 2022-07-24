package com.rsh.JwtTokenTest05.JwtTokenSample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsh.JwtTokenTest05.JwtTokenSample.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	
}
