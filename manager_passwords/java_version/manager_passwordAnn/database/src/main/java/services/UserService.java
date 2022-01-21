package services;

import dao.UserDAOImpl;
import model.Game;
import model.Network;
import model.User;

import java.util.List;

public class UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    public UserService(){}

    public void saveUser(User user){
        userDAO.save(user);
    }

    public void updateUser(User user){
        userDAO.update(user);
    }

    public void deleteUser(User user){
        userDAO.delete(user);
    }

    public List<User> userSearch(String login, String password){
        return userDAO.userSearch(login, password);
    }

    public void AddGame(User user, Game game){
        userDAO.addGameForUser(user, game);
    }

    public void AddNetwork(User user, Network network){
        userDAO.addNetworkForUser(user, network);
    }

    public List<Game> GameList(User user){
        return userDAO.gameList(user);
    }

    public List<Network> NetworkList(User user){
        return userDAO.networksList(user);
    }

    public List<Game> GameSearch(User user, String name, String password){
        return userDAO.gameSearch(user, name, password);
    }

    public List<Network> NetworkSearch(User user, String name, String password){
        return userDAO.networkSearch(user, name, password);
    }

    public void updateGameName(Long game_id, String new_name){
        userDAO.gameUpdateName(game_id, new_name);
    }

    public void updateGamePassword(Long game_id, String new_password){
        userDAO.gameUpdatePassword(game_id, new_password);
    }

    public void updateGameUrl(Long game_id, String new_url){
        userDAO.gameUpdateUrl(game_id, new_url);
    }

    public void updateNetworkName(Long network_id, String new_name){
        userDAO.networkUpdateName(network_id, new_name);
    }

    public void updateNetworkPassword(Long network_id, String new_password){
        userDAO.networkUpdatePassword(network_id, new_password);
    }

    public void updateNetworkUrl(Long network_id, String new_url){
        userDAO.networkUpdateUrl(network_id, new_url);
    }

    public void deleteGame(Long game_id){
        userDAO.gameDelete(game_id);
    }

    public void deleteNetwork(Long network_id){
        userDAO.networkDelete(network_id);
    }
}
