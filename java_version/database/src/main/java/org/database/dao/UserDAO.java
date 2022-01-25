package org.database.dao;

import java.util.List;

import org.database.model.Game;
import org.database.model.Network;
import org.database.model.Other;
import org.database.model.User;

public interface UserDAO {

	public void save(User user);
    public void update(User user);
    public void delete(User user);

    public void addGameForUser(User user, Game game);
    public void addNetworkForUser(User user, Network network);
    public void addOtherForUser(User user, Other other);

    public List<Game> gameList(User user);
    public List<Network> networksList(User user);
    public List<Other> otherList(User user);
    
    public List<User> userSearch(String login, String password);
    public List<Game> gameSearch(User user, String name, String password);
    public List<Network> networkSearch(User user, String name, String password);
    public List<Other> otherSearch(User user, String name, String password);

    public void gameUpdateName(Long game_id, String new_name);
    public void gameUpdatePassword(Long game_id, String new_password);
    public void gameUpdateUrl(Long game_id, String new_url);

    public void networkUpdateName(Long network_id, String new_name);
    public void networkUpdatePassword(Long network_id, String new_password);
    public void networkUpdateUrl(Long network_id, String new_url);

    public void otherUpdateName(Long other_id, String new_name);
    public void otherUpdatePassword(Long other_id, String new_password);
    public void otherUpdateUrl(Long other_id, String new_url);
    
    public void gameDelete(Long game_id);
    public void networkDelete(Long network_id);
    public void otherDelete(Long other_id);
}
