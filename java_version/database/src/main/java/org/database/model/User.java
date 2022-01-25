package org.database.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.exception.ExceptionNotPassword;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Game> games;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Network> networks;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Other> others;
	
	public User() {
	}
	
	public User(String login) throws ExceptionNotPassword{
		throw new ExceptionNotPassword("Вы создаете учетную запись без пароля!");
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
		this.games = new ArrayList<>();
		this.networks = new ArrayList<>();
		this.others = new ArrayList<>();
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setGames(List<Game> game) {
		this.games = game;
	}
	
	public List<Game> getGames(){
		return games;
	}
	
	public void setNetworks(List<Network> network) {
		this.networks = network;
	}
	
	public List<Network> getNetworks(){
		return networks;
	}
	
	public void setOther(List<Other> other) {
		this.others = other;
	}
	
	public List<Other> getOther() {
		return others;
	}
}