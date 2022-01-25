package org.main;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import org.admin.generate_password.GeneratePassword;
import org.admin.hash.Hash;
import org.database.model.Game;
import org.database.model.Network;
import org.database.model.Other;
import org.database.model.User;
import org.database.service.UserService;

public class Main {
	
	public static void CreateUserNull() {
		System.out.println("Вы не ввели логин!");
		System.out.println("Вы не ввели пароль!");
		
		System.out.println("Режим создания учетной записи прерван");
	}
	public static void CreateUserLogin(String login) {
		try {
			User user = new User(login);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("Нельзя создать учетную запись без пароля");
			System.out.println("Учетная запись не создана!");
		}
	}
	public static void CreateUserLoginAndPassword(String login, String password) {
		User user = new User(login, password);
		
		UserService userService = new UserService();
		userService.saveUser(user);
	}
	
	public static void addGameIsEmpty(){
		System.out.println("Вы ничего не ввели");
		System.out.println("Игровая записи преравно!");
	}
	public static void addGameName(User user, String name, Scanner in, Scanner in1) {
		try {
			Game game = new Game(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			
			System.out.print("Сгенерировать пароль? Y/n: ");
			String choice = in.nextLine();
			
			if (choice.equals("Y") || choice.equals("y")) {
				
				System.out.print("Введите длину пароля: ");
				int length = in1.nextInt();
				
				do {
					String password = GeneratePassword.GeneratePassword(length);
					
					System.out.println("Сгенерированный пароль: " + password);
					System.out.print("Сохранить пароль? Y/n: ");
					choice = in.nextLine();
					
					if (choice.equals("Y") || choice.equals("y")) {
						Game game = new Game(name, password, "empty");
						
						UserService userService = new UserService();
						userService.addGame(user, game);
						
						System.out.println("Игровая запись создана!");
						
					}
				}while(choice.equals("N") || choice.equals("n"));
			}
			
			if (choice.equals("N") || choice.equals("n")) {
				System.out.println("Пароль не был введен/сгенерирован");
				System.out.println("Игровая запись не создана!");
			}
		}
	}
	public static void addGameNameAndPassword(User user, String name, String password) {
		try {
			Game game = new Game(name, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Game game = new Game(name, password, "empty");
			
			UserService userService = new UserService();
			userService.addGame(user, game);
			
			System.out.println("Игровая запись создана!");
		}
	}
	public static void addGameNameAndPasswordAndUrl(User user, String name, String password, String url) {
		Game game = new Game(name, password, url);
		
		UserService userService = new UserService();
		userService.addGame(user, game);
		
		System.out.println("Игровая запись создана");
	}
	public static void addGame(User user, Scanner in, Scanner in1) {
		System.out.println("Вы создаете игровую запись");
		
		System.out.print("Введите название для записи: ");
		String name = in.nextLine();
		System.out.print("Введите пароль для записи: ");
		String password = in.nextLine();
		System.out.print("Введите url для записи: ");
		String url = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addGameIsEmpty();
		}
		if (!name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addGameName(user, name, in, in1);
		}
		if (!name.isEmpty() && !password.isEmpty() && url.isEmpty()) {
			addGameNameAndPassword(user, name, password);
		}
		if (!name.isEmpty() && !password.isEmpty() && !url.isEmpty()) {
			addGameNameAndPasswordAndUrl(user, name, password, url);
		}
	}
	
	public static void addNetworkIsEmpty() {
		System.out.println("Вы ничего не ввели");
		System.out.println("Интернет запись не создана");
	}
	public static void addNetworkName(User user, String name, Scanner in, Scanner in1) {
		try {
			Network network = new Network(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {		
			System.out.print("Сгенерированный пароль? Y/n: ");
			String choice = in.nextLine();
			
			if (choice.equals("Y") || choice.equals("y")) {
				System.out.print("Введите длину пароля: ");
				int length = in1.nextInt();
				
				do {
					String password = GeneratePassword.GeneratePassword(length);
					
					System.out.println("Сгенеранный пароль: " + password);
					System.out.print("Сохранить пароль? Y/n: ");
					
					choice = in.nextLine();
					if (choice.equals("Y") || choice.equals("y")) {
						Network network = new Network(name, password, "empty");
						UserService userService = new UserService();
						userService.addNetwork(user, network);
						
						System.out.println("Интернет запись создана!");
					}
				}while(choice.equals("N") || choice.equals("n"));
			}
			if (choice.equals("N") || choice.equals("n")) {
				System.out.println("Пароль не был введен/сгенерирован");
				System.out.println("Интернет запись не создана!");
			}
		}
	}
	public static void addNetworkNameAndPassword(User user, String name, String password) {
		try {
			Network network = new Network(name, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Network network = new Network(name, password, "empty");
			UserService userService = new UserService();
			userService.addNetwork(user, network);
			
			System.out.println("Интернет запись создана!");
		}
	}
	public static void addNetworkNameAndPasswordAndUrl(User user, String name, String password, String url) {
		Network network = new Network(name, password, url);
		
		UserService userService = new UserService();
		userService.addNetwork(user, network);
		
		System.out.println("Интернет запись создана!");
	}
	public static void addNetwork(User user, Scanner in, Scanner in1) {
		System.out.println("Вы создаете интернет запись");
		
		System.out.print("Введите название для записи: ");
		String name = in.nextLine();
		System.out.print("Введите пароль для записи: ");
		String password = in.nextLine();
		System.out.print("Введите url для записи: ");
		String url = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addNetworkIsEmpty();
		}
		if (!name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addNetworkName(user, name, in, in1);
		}
		if (!name.isEmpty() && !password.isEmpty() && url.isEmpty()) {
			addNetworkNameAndPassword(user, name, password);
		}
		if (!name.isEmpty() && !password.isEmpty() && !url.isEmpty()) {
			addNetworkNameAndPasswordAndUrl(user, name, password, url);
		}
	}
	
	public static void addOtherIsEmpty() {
		System.out.println("Вы ничего не ввели");
		System.out.println("Запись не создана");
	}
	public static void addOtherName(User user, String name, Scanner in, Scanner in1) {
		try {
			Other other = new Other(name);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {			
			System.out.println("Сгенерированный пароль? Y/n: ");
			String choice = in.nextLine();
			
			if (choice.equals("Y") || choice.equals("y")) {				
				System.out.print("Введите длину пароля: ");
				int length = in1.nextInt();
				
				do {
					String password = GeneratePassword.GeneratePassword(length);
					
					System.out.println("Сгенерированный пароль: " + password);
					System.out.print("Сохранить пароль? Y/n: ");
					
					choice = in.nextLine();
					if (choice.equals("Y") || choice.equals("y")) {
						Other other = new Other(name, password, "empty");
					}
				}while(choice.equals("N") || choice.equals("n"));
			}
			if (choice.equals("N") ||choice.equals("n")) {
				System.out.println("Пароль не был введен/сгенерирован");
				System.out.println("Запись не создана!");
			}
		}
	}
	public static void addOtherNameAndPassword(User user, String name, String password) {
		try {
			Other other = new Other(name, password);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			Other other = new Other(name, password, "empty");
			
			UserService userService = new UserService();
			userService.addOther(user, other);
			
			System.out.println("Запись создана!");
		}
	}
	public static void addOtherNameAndPasswordAndUrl(User user, String name, String password, String url) {
		Other other = new Other(name, password, url);
		
		UserService userService = new UserService();
		userService.addOther(user, other);
		
		System.out.println("Запись создана!");
	}
	public static void addOther(User user, Scanner in, Scanner in1) {
		System.out.println("Вы создаете запись");
		
		System.out.print("Введите название для записи: ");
		String name = in.nextLine();
		System.out.print("Введите пароль для записи: ");
		String password = in.nextLine();
		System.out.print("Введите url для записи: ");
		String url = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addOtherIsEmpty();
		}
		if (!name.isEmpty() && password.isEmpty() && url.isEmpty()) {
			addOtherName(user, name, in, in1);
		}
		if (!name.isEmpty() && !password.isEmpty() && url.isEmpty()) {
			addOtherNameAndPassword(user, name, password);
		}
		if (!name.isEmpty() && !password.isEmpty() && !url.isEmpty()) {
			addOtherNameAndPasswordAndUrl(user, name, password, url);
		}
	}
	
	public static void recordList(User user) {
		UserService userService = new UserService();
		List<Game> games = userService.gameList(user);
		List<Network> networks = userService.networkList(user);
		List<Other> others = userService.otherList(user);
		
		System.out.println();
		
		for (Game game : games) {
			System.out.println("Тип: игровой");
			System.out.println(game.getId() + ". Имя: " + game.getName());
			System.out.println("Пароль: " + game.getPassword());
			System.out.println("url: " + game.getUrl());
			System.out.println("================");
		}
		for (Network network : networks) {
			System.out.println("Тип: интернет");
			System.out.println(network.getId() + ". Имя: " + network.getName());
			System.out.println("Пароль: " + network.getPassword());
			System.out.println("url: " + network.getUrl());
			System.out.println("================");
		}
		for (Other other : others) {
			System.out.println("Тип: другая");
			System.out.println(other.getId() + ". Имя: " + other.getName());
			System.out.println("Пароль: " + other.getPassword());
			System.out.println("url: " + other.getUrl());
			System.out.println("================");
		}
	}
	
	public static void editRecord(User user, Scanner in, Scanner in1) {
		System.out.println("Режим редактирования");
		System.out.println("Выберите тип записи");
		System.out.println("1) Игровая запись");
		System.out.println("2) Интернет запись");
		System.out.println("3) Другая запись");
		
		System.out.print("Ваш выбор: ");
		
		String choice = in.nextLine();
		
		if (choice.equals("1")) {
			editTypeGame(user, in, in1);
		}
		if (choice.equals("2")) {
			editTypeNetwork(user, in, in1);
		}
		if (choice.equals("3")) {
			editTypeOther(user, in, in1);
		}
	}
	
	public static void editTypeGame(User user, Scanner in, Scanner in1) {
		System.out.println("Редактирования игровой записи");
		System.out.println("1) Изменить название записи");
		System.out.println("2) Изменить пароль записи");
		System.out.println("3) Изменить url записи");
		
		System.out.print("Ваш выбор: ");
		String choice = in.nextLine();
		
		if (choice.equals("1")) {
			editTypeGameName(user, in);
		}
		if (choice.equals("2")) {
			editTypeGamePassword(user, in, in1);
		}
		if (choice.equals("3")) {
			editTypeGameUrl(user, in);
		}
	}
	public static void editTypeGameName(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Введите новое название записи: ");
		String change_name = in.nextLine();
		
		UserService userService = new UserService();
		List<Game> games = userService.gameSearch(user, name, password);
		
		if (games.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		
		if (!games.isEmpty()) {
			Long game_id = null;
			for (Game game : games) {
				game_id = game.getId();
			}
			
			if (change_name.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_name.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateGameName(game_id, change_name);
				
				System.out.println("Игровая запись переименована");
			}
		}
	}
	public static void editTypeGamePassword(User user, Scanner in, Scanner in1) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый пароль для записи: ");
		String change_password = in.nextLine();
		
		UserService userService = new UserService();
		List<Game> games = userService.gameSearch(user, name, password);
		
		if (games.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!games.isEmpty()) {
			Long game_id = null;
			for (Game game : games) {
				game_id = game.getId();
			}
			
			if (!change_password.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateGamePassword(game_id, change_password);
				
				System.out.println("Пароль изменен");
			}
			
			if (change_password.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.print("Сгенерировать пароль? Y/n: ");
				String choice = in.nextLine();
				if (choice.equals("Y") || choice.equals("y")) {					
					System.out.print("Введите длину пароля: ");
					int length = in1.nextInt();
					
					do {
						change_password = GeneratePassword.GeneratePassword(length);
						System.out.println("Сгенерированный пароль: " + change_password);
						System.out.print("Сохранить пароль? Y/n: ");
						choice = in.nextLine();
					}while(choice.equals("N") || choice.equals("n"));
					
					if (choice.equals("Y") || choice.equals("y")) {
						UserService userService1 = new UserService();
						userService1.updateGamePassword(game_id, change_password);
						System.out.println("Пароль изменен");
					}
				}
				if (choice.equals("N") || choice.equals("n")) {
					System.out.println("Нельзя в записи оставлять пароль пустым!");
					System.out.println("Изменение записи прервано!");
				}
			}
		}
	}
	public static void editTypeGameUrl(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый url изменяемой записи: ");
		String change_url = in.nextLine();
		
		UserService userService = new UserService();
		List<Game> games = userService.gameSearch(user, name, password);
		
		if (games.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!games.isEmpty()) {
			Long game_id = null;
			for (Game game : games) {
				game_id = game.getId();
			}
			
			if (change_url.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_url.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateGameUrl(game_id, change_url);
				
				System.out.println("Url изменен");
			}
		}
	}
	
	public static void editTypeNetwork(User user, Scanner in, Scanner in1) {
		System.out.println("Редактирования интернет записи");
		System.out.println("1) Изменить название записи");
		System.out.println("2) Изменить пароль записи");
		System.out.println("3) Изменить url записи");
		
		System.out.print("Ваш выбор: ");
		String choice = in.nextLine();
		
		if (choice.equals("1")) {
			editTypeNetworkName(user, in);
		}
		if (choice.equals("2")) {
			editTypeNetworkPassword(user, in, in1);
		}
		if (choice.equals("3")) {
			editTypeNetworkUrl(user, in);
		}
	}
	public static void editTypeNetworkName(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Введите новое название записи: ");
		String change_name = in.nextLine();
		
		UserService userService = new UserService();
		List<Network> networks = userService.networkSearch(user, name, password);
		
		if (networks.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		
		if (!networks.isEmpty()) {
			Long network_id = null;
			for (Network network : networks) {
				network_id = network.getId();
			}
			
			if (change_name.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_name.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateNetworkName(network_id, change_name);
				
				System.out.println("Интернет запись переименована");
			}
		}
	}
	public static void editTypeNetworkPassword(User user, Scanner in, Scanner in1) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый пароль для записи: ");
		String change_password = in.nextLine();
		
		UserService userService = new UserService();
		List<Network> networks = userService.networkSearch(user, name, password);
		
		if (networks.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!networks.isEmpty()) {
			Long network_id = null;
			for (Network network: networks) {
				network_id = network.getId();
			}
			
			if (!change_password.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateNetworkPassword(network_id, change_password);
				
				System.out.println("Пароль изменен");
			}
			
			if (change_password.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.print("Сгенерировать пароль? Y/n: ");
				String choice = in.nextLine();
				if (choice.equals("Y") || choice.equals("y")) {					
					System.out.print("Введите длину пароля: ");
					int length = in1.nextInt();
					
					do {
						change_password = GeneratePassword.GeneratePassword(length);
						System.out.println("Сгенерированный пароль: " + change_password);
						System.out.print("Сохранить пароль? Y/n: ");
						choice = in.nextLine();
					}while(choice.equals("N") || choice.equals("n"));
					
					if (choice.equals("Y") || choice.equals("y")) {
						UserService userService1 = new UserService();
						userService1.updateNetworkPassword(network_id, change_password);
						System.out.println("Пароль изменен");
					}
				}
				if (choice.equals("N") || choice.equals("n")) {
					System.out.println("Нельзя в записи оставлять пароль пустым!");
					System.out.println("Изменение записи прервано!");
				}
			}
		}
	}
	public static void editTypeNetworkUrl(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый url изменяемой записи: ");
		String change_url = in.nextLine();
		
		UserService userService = new UserService();
		List<Network> networks = userService.networkSearch(user, name, password);
		
		if (networks.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!networks.isEmpty()) {
			Long network_id = null;
			for (Network network : networks) {
				network_id = network.getId();
			}
			
			if (change_url.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_url.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateNetworkUrl(network_id, change_url);
				
				System.out.println("Url изменен");
			}
		}
	}
	
	public static void editTypeOther(User user, Scanner in, Scanner in1) {
		System.out.println("Редактирования записи");
		System.out.println("1) Изменить название записи");
		System.out.println("2) Изменить пароль записи");
		System.out.println("3) Изменить url записи");
		
		System.out.print("Ваш выбор: ");
		String choice = in.nextLine();
		
		if (choice.equals("1")) {
			editTypeOtherName(user, in);
		}
		if (choice.equals("2")) {
			editTypeOtherPassword(user, in, in1);
		}
		if (choice.equals("3")) {
			editTypeOtherUrl(user, in);
		}
	}
	public static void editTypeOtherName(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Введите новое название записи: ");
		String change_name = in.nextLine();
		
		UserService userService = new UserService();
		List<Other> others = userService.otherSearch(user, name, password);
		
		if (others.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		
		if (!others.isEmpty()) {
			Long other_id = null;
			for (Other other : others) {
				other_id = other.getId();
			}
			
			if (change_name.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_name.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateOtherName(other_id, change_name);
				
				System.out.println("Запись переименована");
			}
		}
	}
	public static void editTypeOtherPassword(User user, Scanner in, Scanner in1) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый пароль для записи: ");
		String change_password = in.nextLine();
		
		UserService userService = new UserService();
		List<Other> others = userService.otherSearch(user, name, password);
		
		if (others.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!others.isEmpty()) {
			Long other_id = null;
			for (Other other : others) {
				other_id = other.getId();
			}
			
			if (!change_password.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateOtherPassword(other_id, change_password);
				
				System.out.println("Пароль изменен");
			}
			
			if (change_password.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.print("Сгенерировать пароль? Y/n: ");
				String choice = in.nextLine();
				if (choice.equals("Y") || choice.equals("y")) {					
					System.out.print("Введите длину пароля: ");
					int length = in1.nextInt();
					
					do {
						change_password = GeneratePassword.GeneratePassword(length);
						System.out.println("Сгенерированный пароль: " + change_password);
						System.out.print("Сохранить пароль? Y/n: ");
						choice = in.nextLine();
					}while(choice.equals("N") || choice.equals("n"));
					
					if (choice.equals("Y") || choice.equals("y")) {
						UserService userService1 = new UserService();
						userService1.updateOtherPassword(other_id, change_password);
						System.out.println("Пароль изменен");
					}
				}
				if (choice.equals("N") || choice.equals("n")) {
					System.out.println("Нельзя в записи оставлять пароль пустым!");
					System.out.println("Изменение записи прервано!");
				}
			}
		}
	}
	public static void editTypeOtherUrl(User user, Scanner in) {		
		System.out.print("Имя изменяемой записи: ");
		String name = in.nextLine();
		System.out.print("Пароль изменяемой записи: ");
		String password = in.nextLine();
		System.out.print("Новый url изменяемой записи: ");
		String change_url = in.nextLine();
		
		UserService userService = new UserService();
		List<Other> others = userService.otherSearch(user, name, password);
		
		if (others.isEmpty()) {
			System.out.println("Запись не найдена");
		}
		if (!others.isEmpty()) {
			Long other_id = null;
			for (Other other : others) {
				other_id = other.getId();
			}
			
			if (change_url.isEmpty()) {
				System.out.println("Вы ничего не ввели");
				System.out.println("Изменение прервано!");
			}
			if (!change_url.isEmpty()) {
				UserService userService1 = new UserService();
				userService1.updateOtherUrl(other_id, change_url);
				
				System.out.println("Url изменен");
			}
		}
	}
	
	public static void deleteRecord(User user, Scanner in) {		
		System.out.println("Режим удаления записи");
        System.out.println("1) Игровая запись");
        System.out.println("2) Интернет запись");
        System.out.println("3) Другая запись");
        System.out.print("Ваш выбор: ");
        String choice = in.nextLine();

        if (choice.equals("1")){
            deleteGameRecord(user, in);
        }
        if (choice.equals("2")){
            deleteNetworkRecord(user, in);
        }
        if (choice.equals("3")) {
        	deleteOtherRecord(user, in);
        }
	}
	public static void deleteGameRecord(User user, Scanner in) {		
		System.out.print("Имя записи: ");
		String name = in.nextLine();
		System.out.print("Пароль записи: ");
		String password = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty()) {
			System.out.println("Вы ничего не ввели");
			System.out.println("Удаление прервано!");
		}
		if (!name.isEmpty() && !password.isEmpty()) {
			UserService userService = new UserService();
			List<Game> games = userService.gameSearch(user, name, password);
			
			if (games.isEmpty()) {
				System.out.println("Запись не найдена");
			}
			if (!games.isEmpty()) {
				Long game_id = null;
				for (Game game: games) {
					game_id = game.getId();
				}
				userService.deleteGame(game_id);
				
				System.out.println("Запись удалена!");
			}
		}
	}
	public static void deleteNetworkRecord(User user, Scanner in) {		
		System.out.print("Имя записи: ");
		String name = in.nextLine();
		System.out.print("Пароль записи: ");
		String password = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty()) {
			System.out.println("Вы ничего не ввели");
			System.out.println("Удаление прервано!");
		}
		if (!name.isEmpty() && !password.isEmpty()) {
			UserService userService = new UserService();
			List<Network> networks = userService.networkSearch(user, name, password);
			
			if (networks.isEmpty()) {
				System.out.println("Запись не найдена");
			}
			if (!networks.isEmpty()) {
				Long network_id = null;
				for (Network network : networks) {
					network_id = network.getId();
				}
				userService.deleteNetwork(network_id);
				
				System.out.println("Запись удалена!");
			}
		}
	}
	public static void deleteOtherRecord(User user, Scanner in) {		
		System.out.print("Имя записи: ");
		String name = in.nextLine();
		System.out.print("Пароль записи: ");
		String password = in.nextLine();
		
		if (name.isEmpty() && password.isEmpty()) {
			System.out.println("Вы ничего не ввели");
			System.out.println("Удаление прервано!");
		}
		if (!name.isEmpty() && !password.isEmpty()) {
			UserService userService = new UserService();
			List<Other> others = userService.otherSearch(user, name, password);
			
			if (others.isEmpty()) {
				System.out.println("Запись не найдена");
			}
			if (!others.isEmpty()) {
				Long other_id = null;
				for (Other other : others) {
					other_id = other.getId();
				}
				userService.deleteNetwork(other_id);
				
				System.out.println("Запись удалена!");
			}
		}
	}
	
	public static void Registry() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Введите логин: ");
		String login = in.nextLine();
		System.out.print("Введите пароль: ");
		String password = in.nextLine();
		
		try {
			login = Hash.HashString(login);
			password = Hash.HashString(password);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (login.isEmpty() && password.isEmpty()) {
			CreateUserNull();
		}
		if (!login.isEmpty() && password.isEmpty()) {
			CreateUserLogin(login);
		}
		if (!login.isEmpty() && !password.isEmpty()) {
			CreateUserLoginAndPassword(login, password);
		}
		in.close();
	}
	public static void Authorization() {
		Scanner in = new Scanner(System.in);
		Scanner in1 = new Scanner(System.in);
		
		System.out.print("Введите логин: ");
		String login = in.nextLine();
		System.out.print("Введите пароль: ");
		String password = in.nextLine();
		
		try {
			login = Hash.HashString(login);
			password = Hash.HashString(password);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		UserService userService = new UserService();
		List<User> users = userService.userSearch(login, password);
		
		if (users.isEmpty()) {
			System.out.println("Учетная запись не найдена");
		}
		
		if (!users.isEmpty()) {
			System.out.println("1) Создать игровую запись");
			System.out.println("2) Создать интернет запись");
			System.out.println("3) Создать запись");
			System.out.println("4) Просмотреть все записи");
			System.out.println("5) Редактировать запись");
			System.out.println("6) Удалить запись");
			
			User user = null;
			for(User user1 : users) {
				user = user1;
			}
			
			System.out.print("Ваш выбор: ");
			String choice = in.nextLine();
			
			while(!choice.equals("Exit") || !choice.equals("exit") || !choice.equals("e")) {
				
				if (choice.equals("1")) {
					addGame(user, in, in1);
				}
				if (choice.equals("2")) {
					addNetwork(user, in, in1);
				}
				if (choice.equals("3")) {
					addOther(user, in, in1);
				}
				if (choice.equals("4")) {
					recordList(user);
				}
				if (choice.equals("5")) {
					editRecord(user, in, in1);
				}
				if (choice.equals("6")) {
					deleteRecord(user, in);
				}
				if (choice.equals("7")) {
					try {
						String name_test = Hash.HashString(user.getLogin());
						String password_test = Hash.HashString(user.getPassword());
						
						System.out.println(name_test);
						System.out.println(password_test);
					}catch(Exception e) {
						System.out.print(e.getMessage());
					}
				}
				if (choice.equals("Exit") || choice.equals("exit") || choice.equals("e")) {
					break;
				}
				System.out.println();
				System.out.println("Чтобы выйти из приложения введите Exit,exit,e");
				System.out.print("Ваш выбор: ");
				choice = in.nextLine();
				if (choice.equals("Exit") || choice.equals("exit") || choice.equals("e")) {
					break;
				}
			}
			in.close();
			in1.close();
			System.out.println("Выход из приложения");
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("1) Создать учетную запись");
		System.out.println("2) Войти в учетную запись");
		
		System.out.print("Ваш выбор: ");
		Scanner in = new Scanner(System.in);
		String choice = in.nextLine();
		
		if (choice.equals("1")) {
			Registry();
		}
		if (choice.equals("2")) {
			Authorization();
		}
		in.close();
	}
}
