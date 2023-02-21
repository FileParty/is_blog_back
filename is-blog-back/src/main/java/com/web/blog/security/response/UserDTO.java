package com.web.blog.security.response;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TBL_USER")
@Data
public class UserDTO {
	
	@Id
	@Column(name="USER_ID")
	private String userId;
	
	@Column(name="USER_NM")
	private String user_nm;
	
	@Column(name="USER_PWD")
	private String user_pwd;
	
	@Column(name="IS_USE")
	private String is_use;
	
	@OneToMany
	@JoinTable(name = "TBL_USER_AUTH", joinColumns = @JoinColumn(name="USER_ID"),
			inverseJoinColumns = @JoinColumn(name="AUTH_ID")
			)
	private Set<AuthDTO> authorities;

}
