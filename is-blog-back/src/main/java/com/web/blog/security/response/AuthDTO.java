package com.web.blog.security.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="TBL_AUTH")
@Data
public class AuthDTO {
	
	@Id
	@Column(name="AUTH_ID")
	private String auth_id;
	
	@Column(name="AUTH_NM")
	private String auth_nm;

}
