package org.database.dao;

import java.util.List;

import org.database.model.Game;
import org.database.model.Network;
import org.database.model.Other;
import org.database.model.User;
import org.database.util.HibernateSessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOImpl implements UserDAO{

	@Override
    public void save(User user){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
    @Override
    public void update(User user){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }
    @Override
    public void delete(User user){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }
    
    @Override
    public void addGameForUser(User user, Game game){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            game.setUser(user);
            session.save(game);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void addNetworkForUser(User user, Network network){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            network.setUser(user);
            session.save(network);
            transaction.commit();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void addOtherForUser(User user, Other other) {
    	Session session = HibernateSessionUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
			transaction = session.beginTransaction();
			other.setUser(user);
			session.save(other);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public List<Game> gameList(User user){
        String sql = "from Game where user = :user";
        List<Game> games = (List<Game>) HibernateSessionUtil.getSessionFactory()
                .openSession().createQuery(sql)
                .setParameter("user", user)
                .list();

        return games;
    }
    @Override
    public List<Network> networksList(User user){
        String sql = "from Network where user = :user";
        List<Network> networks = (List<Network>) HibernateSessionUtil.getSessionFactory()
                .openSession().createQuery(sql)
                .setParameter("user", user)
                .list();

        return networks;
    }
    @Override
    public List<Other> otherList(User user){
    	String sql = "from Other where user = :user";
    	List<Other> others = (List<Other>) HibernateSessionUtil.getSessionFactory()
    			.openSession().createQuery(sql)
    			.setParameter("user", user)
    			.list();
    	
    	return others;
    }

    @Override
    public List<User> userSearch(String login, String password){
        String sql = "from User where login = :login AND password = :password";
        List<User> users = (List<User>) HibernateSessionUtil.getSessionFactory()
                .openSession().createQuery(sql)
                .setParameter("login", login)
                .setParameter("password", password)
                .list();

        return users;
    }
    @Override
    public List<Game> gameSearch(User user, String name, String password){
        String sql = "from Game where name = :name and password = :password and user = :user";
        List<Game> games = (List<Game>) HibernateSessionUtil.getSessionFactory()
                .openSession().createQuery(sql)
                .setParameter("name", name)
                .setParameter("password", password)
                .setParameter("user", user)
                .list();


        return games;
    }
    @Override
    public List<Network> networkSearch(User user, String name, String password){
        String sql = "from Network where name = :name and password = :password and user = :user";
        List<Network> networks = (List<Network>) HibernateSessionUtil.getSessionFactory()
                .openSession().createQuery(sql)
                .setParameter("name", name)
                .setParameter("password", password)
                .setParameter("user", user)
                .list();

        return networks;
    }
    @Override
    public List<Other> otherSearch(User user, String name, String password){
    	String sql = "from Other where name = :name and password = :password and user = :user";
    	List<Other> others = (List<Other>) HibernateSessionUtil.getSessionFactory()
    			.openSession().createQuery(sql)
    			.setParameter("name", name)
    			.setParameter("password", password)
    			.setParameter("user", user)
    			.list();
    	
    	return others;
    }

    @Override
    public void gameUpdateName(Long game_id, String new_name){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Game game = session.get(Game.class, game_id);
            game.setName(new_name);
            session.update(game);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void gameUpdatePassword(Long game_id, String new_password){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Game game = session.get(Game.class, game_id);
            game.setPassword(new_password);
            session.update(game);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void gameUpdateUrl(Long game_id, String new_url){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Game game = session.get(Game.class, game_id);
            game.setUrl(new_url);
            session.update(game);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public void networkUpdateName(Long network_id, String new_name){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();

            Network network = session.get(Network.class, network_id);
            network.setName(new_name);
            session.update(network);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void networkUpdatePassword(Long network_id, String new_password){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Network network = session.get(Network.class, network_id);
            network.setPassword(new_password);
            session.update(network);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void networkUpdateUrl(Long network_id, String new_url){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Network network = session.get(Network.class, network_id);
            network.setUrl(new_url);
            session.update(network);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

    @Override
    public void otherUpdateName(Long other_id, String new_name) {
    	Session session = HibernateSessionUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
			transaction = session.beginTransaction();
			
			Other other = session.get(Other.class, other_id);
			other.setName(new_name);
			session.update(other);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
    }
    @Override
    public void otherUpdatePassword(Long other_id, String new_password) {
    	Session session = HibernateSessionUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
			transaction = session.beginTransaction();
			
			Other other = session.get(Other.class, other_id);
			other.setPassword(new_password);
			session.update(other);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
    }
    @Override
    public void otherUpdateUrl(Long other_id, String new_url) {
    	Session session = HibernateSessionUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
			transaction = session.beginTransaction();
			
			Other other = session.get(Other.class, other_id);
			other.setUrl(new_url);
			session.update(other);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
    }
    
    @Override
    public void gameDelete(Long game_id){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Game game = session.get(Game.class, game_id);
            session.delete(game);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void networkDelete(Long network_id){
        Session session = HibernateSessionUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Network network = session.get(Network.class, network_id);
            session.delete(network);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    @Override
    public void otherDelete(Long other_id) {
    	Session session = HibernateSessionUtil.getSessionFactory().openSession();
    	Transaction transaction = null;
    	try {
			transaction = session.beginTransaction();
			
			Other other = session.get(Other.class, other_id);
			session.delete(other);
			transaction.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			session.close();
		}
    }
}
