package com.web.blog.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.web.blog.security.response.UserDTO;


public interface UserRepository 
	extends JpaRepository<UserDTO, String> { // JpaRepository 참고 : https://araikuma.tistory.com/329
	
	@EntityGraph(attributePaths = "authorities")
    Optional<UserDTO> findOneWithAuthoritiesByUserId(String userId);

}
