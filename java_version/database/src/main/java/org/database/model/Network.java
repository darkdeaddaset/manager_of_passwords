package org.database.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.exception.ExceptionNotPassword;
import org.exception.ExceptionNotUrl;

@Entity
@Table(name = "networks")
public class Network {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "password")
	private String password;
	@Column(name = "url")
	private String url;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
	public Network() {
		
	}
	
	public Network(String name) throws ExceptionNotPassword{
		throw new ExceptionNotPassword("Вы создаете интернет запись без пароля!");
	}
	
	public Network(String name, String password) throws ExceptionNotUrl{
		throw new ExceptionNotUrl("Вы создаете интерент запись без url");
	}
	
	public Network(String name, String password, String url) {
		this.name = name;
		this.password = password;
		this.url = url;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
}