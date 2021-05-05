package com.example.requestvacation.domain.entitiy;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "usr_master")
@DynamicInsert
public class UserInfo extends Time implements UserDetails{

	@Id
    @Column(length = 20, nullable = false)
    private String usrId;
    
    @Column(length = 200, nullable = false)
	private String usrPw;
    
    @Column(length = 10)
	private String usrName;
    
    @Column(length = 10)
	private String usrRole;
    
    @Column(columnDefinition ="numeric default 15.0")
	private Double  availableVacDs;
    
    @Column(columnDefinition ="numeric default 0.0")
    private Double enrolledVacDs;

    @Builder
	public UserInfo(Long usrSeq, String usrId, String usrPw, String usrName, String usrRole, Double availableVacDs, Double enrolledVacDs) {
		this.usrId = usrId;
		this.usrPw = usrPw;
		this.usrName = usrName;
		this.usrRole = usrRole;
		this.availableVacDs = availableVacDs;
		this.enrolledVacDs = enrolledVacDs;
	}

	  @Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    for (String role : usrRole.split(",")) {
	      roles.add(new SimpleGrantedAuthority(role));
	    }
	    return roles;
	  }

	  @Override
	  public String getUsername() {
	    return usrId;
	  }

	  @Override
	  public String getPassword() {
	    return usrPw;
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true; 
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true; 
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true; 
	  }

	  @Override
	  public boolean isEnabled() {
	    return true; 
	  }
	   public void setUsrPw(String usrPw) {
		this.usrPw = usrPw;
	   }
}