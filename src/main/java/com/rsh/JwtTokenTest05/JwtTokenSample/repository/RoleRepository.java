package com.rsh.JwtTokenTest05.JwtTokenSample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rsh.JwtTokenTest05.JwtTokenSample.domain.Role;


public interface RoleRepository  extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
