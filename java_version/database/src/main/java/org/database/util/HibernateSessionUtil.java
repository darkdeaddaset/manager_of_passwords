package org.database.util;

import org.database.model.Game;
import org.database.model.Network;
import org.database.model.Other;
import org.database.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionUtil {

	private static SessionFactory sessionFactory;
	
	private HibernateSessionUtil() {}
	
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration().configure();
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Game.class);
				configuration.addAnnotatedClass(Network.class);
				configuration.addAnnotatedClass(Other.class);
				StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties());
				sessionFactory = configuration.buildSessionFactory(builder.build());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return sessionFactory;
	}
}