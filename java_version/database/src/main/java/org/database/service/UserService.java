package org.database.service;

import java.util.List;

import org.database.dao.UserDAOImpl;
import org.database.model.Game;
import org.database.model.Network;
import org.database.model.Other;
import org.database.model.User;

public class UserService {

	private UserDAOImpl userDAO = new UserDAOImpl();
	
	public UserService() {
	}
	
	public void saveUser(User user) {
		userDAO.save(user);
	}
	public void updateUser(User user) {
		userDAO.update(user);
	}
	public void deleteUser(User user) {
		userDAO.delete(user);
	}
	
	public void addGame(User user, Game game) {
		userDAO.addGameForUser(user, game);
	}
	public void addNetwork(User user, Network network) {
		userDAO.addNetworkForUser(user, network);
	}
	public void addOther(User user, Other other) {
		userDAO.addOtherForUser(user, other);
	}
	
	public List<Game> gameList(User user){
		return userDAO.gameList(user);
	}
	public List<Network> networkList(User user){
		return userDAO.networksList(user);
	}
	public List<Other> otherList(User user){
		return userDAO.otherList(user);
	}
	
	public List<User> userSearch(String login, String password){
		return userDAO.userSearch(login, password);
	}
	public List<Game> gameSearch(User user, String name, String password){
		return userDAO.gameSearch(user, name, password);
	}
	public List<Network> networkSearch(User user, String name, String password){
		return userDAO.networkSearch(user, name, password);
	}
	public List<Other> otherSearch(User user, String name, String password){
		return userDAO.otherSearch(user, name, password);
	}
	
	public void updateGameName(Long game_id, String new_name) {
		userDAO.gameUpdateName(game_id, new_name);
	}
	public void updateGamePassword(Long game_id, String new_password) {
		userDAO.gameUpdatePassword(game_id, new_password);
	}
	public void updateGameUrl(Long game_id, String new_url) {
		userDAO.gameUpdateUrl(game_id, new_url);
	}
	public void updateNetworkName(Long network_id, String new_name) {
		userDAO.networkUpdateName(network_id, new_name);
	}
	public void updateNetworkPassword(Long network_id, String new_password) {
		userDAO.networkUpdatePassword(network_id, new_password);
	}
	public void updateNetworkUrl(Long network_id, String new_url) {
		userDAO.networkUpdateUrl(network_id, new_url);
	}
	public void updateOtherName(Long other_id, String new_name) {
		userDAO.otherUpdateName(other_id, new_name);
	}
	public void updateOtherPassword(Long other_id, String new_password) {
		userDAO.otherUpdatePassword(other_id, new_password);
	}
	public void updateOtherUrl(Long other_id, String new_url) {
		userDAO.otherUpdateUrl(other_id, new_url);
	}
	
	public void deleteGame(Long game_id) {
		userDAO.gameDelete(game_id);
	}
	public void deleteNetwork(Long network_id) {
		userDAO.networkDelete(network_id);
	}
	public void deleteOther(Long other_id) {
		userDAO.otherDelete(other_id);
	}
}
