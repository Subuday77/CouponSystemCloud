package com.couponsystem.CouponSystemSpring.beans;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Logins")
@Component
@Scope(value="prototype")
public class Login {
	
	private long subjectId;
	private UUID token;
	private int accessLevel;
	private long timestamp;
	
	public Login(long subjectId, UUID token, int accessLevel, long timestamp) {
		super();
		this.subjectId = subjectId;
		this.token = token;
		this.accessLevel = accessLevel;
		this.timestamp = timestamp;
	}
	public Login() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue
	@Column(unique = true, nullable = false)
	public UUID getToken() {
		return token;
	}
	public void setToken(UUID token) {
		this.token = token;
	}
	@Column	
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	@Column
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
	@Column
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "Login [subjectId=" + subjectId + ", token=" + token + ", accessLevel=" + accessLevel + ", timestamp="
				+ timestamp + "]";
	}
		
	

}
