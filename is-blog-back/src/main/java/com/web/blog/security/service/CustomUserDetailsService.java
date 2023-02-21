package com.web.blog.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.blog.security.repository.UserRepository;
import com.web.blog.security.response.UserDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	@Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		return userRepository.findOneWithAuthoritiesByUserId(userId)
                .map(user -> createUser(userId, user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 존재 하지 않음."));
    }
	
	/**Security User 정보를 생성한다. */
    private User createUser(String userId, UserDTO userDTO) {
        if(!"Y".equals(userDTO.getIs_use())){
            throw new BadCredentialsException(userId + " -> 활성화 되어있지 않습니다.");
        }
        List<GrantedAuthority> grantedAuthorities = userDTO.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuth_id()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(
        		userDTO.getUserId(),
        		userDTO.getUser_pwd(),
                grantedAuthorities);
    }

}
