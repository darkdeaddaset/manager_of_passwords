import exceptions.ExceptionNotPassword;
import generate_pass.GeneratePassword;
import model.Game;
import model.Network;
import model.User;
import services.UserService;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void Registry_LoginAndPasswordIsEmpty(){
        System.out.println("Вы ничего не ввели");
        System.out.println("Учетная запись не создана!");
    }
    public static void Registry_LoginNotIsEmptyAndPasswordIsEmpty(String login){
        try{
            User user = new User(login);
        }catch (ExceptionNotPassword e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("Нельзя создать учетную запись без пароля");
            System.out.println("Учетная запись не создана");
        }
    }
    public static void Registry_LoginAndPassword(String login, String password){
        User user = new User(login, password);

        UserService userService = new UserService();
        userService.saveUser(user);
    }
    public static void Registry(){
        Scanner in = new Scanner(System.in);
        System.out.println("Для продолжения нажмите Enter");
        String choice = in.nextLine();

        do {
            System.out.print("Введите логин: ");
            String login = in.nextLine();
            System.out.print("Введите пароль: ");
            String password = in.nextLine();

            System.out.println("===============");
            System.out.println("Ваш логин: " + login);
            System.out.println("Ваш пароль: " + password);
            System.out.print("Все верно? Y/n: ");
            choice = in.nextLine();

            if (choice.equals("Y") || choice.equals("y")){
                if (login.isEmpty() && password.isEmpty()){
                    Registry_LoginAndPasswordIsEmpty();
                }
                if (!login.isEmpty() && password.isEmpty()){
                    Registry_LoginNotIsEmptyAndPasswordIsEmpty(login);
                }
                if (!login.isEmpty() && !password.isEmpty()){
                    Registry_LoginAndPassword(login, password);
                }
            }
        }while (choice.equals("N") || choice.equals("n"));
        in.close();
    }

    public static void AddGame(List<User> users){
        System.out.println("Вы создаете игровую запись");
        Scanner in1 = new Scanner(System.in);

        System.out.print("Введите название для записи: ");
        String name = in1.nextLine();
        System.out.print("Введите пароль для записи: ");
        String password = in1.nextLine();
        System.out.print("Введите url для записи: ");
        String url = in1.nextLine();

        if (name.isEmpty() && password.isEmpty() && url.isEmpty()){
            AddGame_NameAndPasswordAndUrlIsEmpty();
        }
        if (!name.isEmpty() && password.isEmpty() && url.isEmpty()){
            AddGame_NameNotIsEmptyAndPasswordAndUrlIsEmpty(users, name);
        }
        if (!name.isEmpty() && !password.isEmpty() && url.isEmpty()){
            AddGame_NameAndPasswordNotIsEmptyAndUrlIsEmpty(users, name, password);
        }
        if (!name.isEmpty() && !password.isEmpty() && !url.isEmpty()){
            AddGame_NameAndPasswordAndUrlNotIsEmpty(users, name, password, url);
        }
        in1.close();
    }
    public static void AddGame_NameAndPasswordAndUrlIsEmpty(){
        System.out.println("Вы ничего не ввели");
        System.out.println("Игровая запись не создана");
    }
    public static void AddGame_NameNotIsEmptyAndPasswordAndUrlIsEmpty(List<User> users,String name){
        try{
            Game game = new Game(name);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            Scanner in = new Scanner(System.in);

            System.out.print("Сгенерировать пароль? Y/n: ");
            String choice = in.nextLine();

            if (choice.equals("Y") || choice.equals("y")){
                Scanner in1 = new Scanner(System.in);
                System.out.print("Введите длину пароля: ");
                int length = in1.nextInt();

                do{
                    String password = GeneratePassword.GeneratePassword(length);

                    System.out.println("Сгенерированный пароль: " + password);
                    System.out.print("Сохранить пароль? Y/n: ");

                    choice = in.nextLine();
                    if (choice.equals("Y") || choice.equals("y")){
                        User user = null;
                        for (User user1 : users){
                            user = user1;
                        }

                        Game game = new Game(name, password, "empty");
                        UserService userService = new UserService();
                        userService.AddGame(user, game);

                        System.out.println("Игровая запись создана!");
                    }
                }while (choice.equals("N") || choice.equals("n"));

                in1.close();
                in.close();
            }
            if (choice.equals("N") || choice.equals("n")){
                System.out.println("Пароль не был введен/сгенерирован");
                System.out.println("Игровая запись не создана");

                in.close();
            }
        }
    }
    public static void AddGame_NameAndPasswordNotIsEmptyAndUrlIsEmpty(List<User> users, String name, String password){
        try{
            Game game = new Game(name, password);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            User user = null;
            for (User user1 : users){
                user = user1;
            }

            Game game = new Game(name, password, "empty");
            UserService userService = new UserService();
            userService.AddGame(user, game);

            System.out.println("Игровая запись создана!");
        }
    }
    public static void AddGame_NameAndPasswordAndUrlNotIsEmpty(List<User> users, String name, String password, String url){
        User user = null;
        for (User user1 : users){
            user = user1;
        }

        Game game = new Game(name, password, url);
        UserService userService = new UserService();
        userService.AddGame(user, game);
    }

    public static void AddNetwork(List<User> users){
        System.out.println("Вы создаете интернет запись");
        Scanner in = new Scanner(System.in);

        System.out.print("Введите название для записи: ");
        String name = in.nextLine();
        System.out.print("Введите пароль для записи: ");
        String password = in.nextLine();
        System.out.print("Введите url для записи: ");
        String url = in.nextLine();

        if (name.isEmpty() && password.isEmpty() && url.isEmpty()){
            AddNetwork_NameAndPasswordAndUrlIsEmpty();
        }
        if (!name.isEmpty() && password.isEmpty() && url.isEmpty()){
            AddNetwork_NameNotIsEmptyAndPasswordAndUrlIsEmpty(users, name);
        }
        if (!name.isEmpty() && !password.isEmpty() && url.isEmpty()){
            AddNetwork_NameAndPasswordNotIsEmptyAndUrlIsEmpty(users, name, password);
        }
        if (!name.isEmpty() && !password.isEmpty() && !url.isEmpty()){
            AddNetwork_NameAndPasswordAndUrlNotIsEmpty(users, name, password, url);
        }
        in.close();
    }
    public static void AddNetwork_NameAndPasswordAndUrlIsEmpty(){
        System.out.println("Вы ничего не ввели");
        System.out.println("Интернет запись не создана");
    }
    public static void AddNetwork_NameNotIsEmptyAndPasswordAndUrlIsEmpty(List<User> users, String name){
        try{
            Network game = new Network(name);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            Scanner in = new Scanner(System.in);

            System.out.print("Сгенерировать пароль? Y/n: ");
            String choice = in.nextLine();

            if (choice.equals("Y") || choice.equals("y")){
                Scanner in1 = new Scanner(System.in);
                System.out.print("Введите длину пароля: ");
                int length = in1.nextInt();

                do{
                    String password = GeneratePassword.GeneratePassword(length);

                    System.out.println("Сгенерированный пароль: " + password);
                    System.out.print("Сохранить пароль? Y/n: ");

                    choice = in.nextLine();
                    if (choice.equals("Y") || choice.equals("y")){
                        User user = null;
                        for (User user1 : users){
                            user = user1;
                        }

                        Network network = new Network(name, password, "empty");
                        UserService userService = new UserService();
                        userService.AddNetwork(user, network);

                        System.out.println("Интернет запись создана!");
                    }
                }while (choice.equals("N") || choice.equals("n"));

                in1.close();
                in.close();
            }
            if (choice.equals("N") || choice.equals("n")){
                System.out.println("Пароль не был введен/сгенерирован");
                System.out.println("Интернет запись не создана");

                in.close();
            }
        }
    }
    public static void AddNetwork_NameAndPasswordNotIsEmptyAndUrlIsEmpty(List<User> users, String name, String password){
        try{
            Network network = new Network(name, password);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            User user = null;
            for (User user1 : users){
                user = user1;
            }

            Network network = new Network(name, password, "empty");
            UserService userService = new UserService();
            userService.AddNetwork(user, network);

            System.out.println("Интернет запись создана!");
        }
    }
    public static void AddNetwork_NameAndPasswordAndUrlNotIsEmpty(List<User> users, String name, String password, String url){
        User user = null;
        for (User user1 : users){
            user = user1;
        }

        Network network = new Network(name, password, url);
        UserService userService = new UserService();
        userService.AddNetwork(user, network);
    }

    public static void RecordList(List<User> users){
        User user = null;
        for (User user1 : users){
            user = user1;
        }

        UserService userService = new UserService();
        List<Game> games = userService.GameList(user);
        for (Game game : games){
            System.out.println("Тип: игровая");
            System.out.println(game.getId() + ". Имя: " + game.getName());
            System.out.println("Пароль: " + game.getPassword());
            System.out.println("Url: " + game.getUrl());
            System.out.println("==================");
        }
        List<Network> networks = userService.NetworkList(user);
        for (Network network : networks){
            System.out.println("Тип: интернет");
            System.out.println(network.getId() + ". Имя: " + network.getName());
            System.out.println("Пароль: " + network.getPassword());
            System.out.println("Url: " + network.getUrl());
            System.out.println("==================");
        }
    }

    public static void EditRecord(List<User> users){
        System.out.println("Режим редактирования");
        System.out.println("Выберите тип записи");
        System.out.println("1) Игровая запись");
        System.out.println("2) Интернет запись");

        System.out.print("Ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("1")){
            EditTypeGame(users);
            in.close();
        }
        if (choice.equals("2")){
            EditTypeNetwork(users);
            in.close();
        }
    }
    public static void EditTypeGame(List<User> users){
        System.out.println("Вы выбрали редактирования игровой записи");
        System.out.println("1) Изменить название записи");
        System.out.println("2) Изменить пароль записи");
        System.out.println("3) Изменить url записи");

        System.out.print("Ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("1")){
            EditGameRecordName(users);
        }
        if (choice.equals("2")){
            EditGameRecordPassword(users);
        }
        if (choice.equals("3")){
            EditGameRecordUrl(users);
        }
        in.close();
    }
    public static void EditGameRecordName(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новое название записи: ");
        String change_name = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Game> games = userService.GameSearch(user, name, password);

        if (games.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!games.isEmpty()){
            Long temp = null;
            for (Game game1 : games){
                temp = game1.getId();
            }

            if (change_name.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.println("Изменение прервано!");
            }
            if (!change_name.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateGameName(temp, change_name);
                System.out.println("Игровая запись переименована");
            }
        }
        in.close();
    }
    public static void EditGameRecordPassword(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новый пароль записи: ");
        String change_password = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Game> games = userService.GameSearch(user, name, password);

        if (games.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!games.isEmpty()){
            Long temp = null;
            for (Game game1 : games){
                temp = game1.getId();
            }

            if (!change_password.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateGamePassword(temp, change_password);
                System.out.println("Пароль изменен");
            }

            if (change_password.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.print("Сгенерировать пароль? Y/n: ");
                String choice = in.nextLine();
                if (choice.equals("Y") || choice.equals("y")){
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Введите длину пароля: ");
                    int length = in1.nextInt();

                    do {
                        change_password = GeneratePassword.GeneratePassword(length);
                        System.out.println("Сгенерированный пароль: " + change_password);
                        System.out.print("Сохранить? Y/n: ");
                        choice = in.nextLine();
                    }while (choice.equals("N") || choice.equals("n"));

                    if (choice.equals("Y") || choice.equals("Y")){
                        UserService userService1 = new UserService();
                        userService1.updateGamePassword(temp, change_password);
                        System.out.println("Пароль изменен");
                    }
                    in1.close();
                }
                if (choice.equals("N") || choice.equals("n")){
                    System.out.println("Нельзя в записи оставлять пароль пустым!");
                    System.out.println("Изменение записи преравно!");
                }
            }
        }
        in.close();
    }
    public static void EditGameRecordUrl(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новый url записи: ");
        String change_url = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Game> games = userService.GameSearch(user, name, password);

        if (games.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!games.isEmpty()){
            Long temp = null;
            for (Game game1 : games){
                temp = game1.getId();
            }

            if (change_url.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.println("Изменение преравно!");
            }
            if (!change_url.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateGameUrl(temp, change_url);
                System.out.println("Url изменен");
            }
        }
        in.close();
    }
    public static void EditTypeNetwork(List<User> users){
        System.out.println("Вы выбрали редактирования интернет записи");
        System.out.println("1) Изменить название записи");
        System.out.println("2) Изменить пароль записи");
        System.out.println("3) Изменить url записи");

        System.out.print("Ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("1")){
            EditNetworkRecordName(users);
        }
        if (choice.equals("2")){
            EditTypeNetworkRecordPassword(users);
        }
        if (choice.equals("3")){
            EditTypeNetworkRecordUrl(users);
        }
        in.close();
    }
    public static void EditNetworkRecordName(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новое название записи: ");
        String change_name = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Network> networks = userService.NetworkSearch(user, name, password);

        if (networks.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!networks.isEmpty()){
            Long temp = null;
            for (Network network : networks){
                temp = network.getId();
            }

            if (change_name.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.println("Изменение прервано!");
            }
            if (!change_name.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateNetworkName(temp, change_name);
                System.out.println("Интернет запись переименована");
            }
        }
        in.close();
    }
    public static void EditTypeNetworkRecordPassword(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новый пароль записи: ");
        String change_password = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Network> networks = userService.NetworkSearch(user, name, password);

        if (networks.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!networks.isEmpty()){
            Long temp = null;
            for (Network network : networks){
                temp = network.getId();
            }

            if (!change_password.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateNetworkPassword(temp, change_password);
                System.out.println("Пароль изменен");
            }

            if (change_password.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.print("Сгенерировать пароль? Y/n: ");
                String choice = in.nextLine();
                if (choice.equals("Y") || choice.equals("y")){
                    Scanner in1 = new Scanner(System.in);
                    System.out.print("Введите длину пароля: ");
                    int length = in1.nextInt();

                    do {
                        change_password = GeneratePassword.GeneratePassword(length);
                        System.out.println("Сгенерированный пароль: " + change_password);
                        System.out.print("Сохранить? Y/n: ");
                        choice = in.nextLine();
                    }while (choice.equals("N") || choice.equals("n"));

                    if (choice.equals("Y") || choice.equals("Y")){
                        UserService userService1 = new UserService();
                        userService1.updateNetworkPassword(temp, change_password);
                        System.out.println("Пароль изменен");
                    }
                    in1.close();
                }
                if (choice.equals("N") || choice.equals("n")){
                    System.out.println("Нельзя в записи оставлять пароль пустым!");
                    System.out.println("Изменение записи прервано!");
                }
            }
        }
        in.close();
    }
    public static void EditTypeNetworkRecordUrl(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя изменяемой записи: ");
        String name = in.nextLine();
        System.out.print("Пароль изменяемой записи: ");
        String password = in.nextLine();
        System.out.print("Введите новый url записи: ");
        String change_url = in.nextLine();

        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }
        List<Network> networks = userService.NetworkSearch(user, name, password);

        if (networks.isEmpty()){
            System.out.println("Запись не найдена");
        }
        if (!networks.isEmpty()){
            Long temp = null;
            for (Network network : networks){
                temp = network.getId();
            }

            if (change_url.isEmpty()){
                System.out.println("Вы ничего не ввели");
                System.out.println("Изменение преравно!");
            }
            if (!change_url.isEmpty()){
                UserService userService1 = new UserService();
                userService1.updateNetworkUrl(temp, change_url);
                System.out.println("Url изменен");
            }
        }
        in.close();
    }

    public static void DeleteRecord(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.println("Режим удаления записи");
        System.out.println("1) Игровая запись");
        System.out.println("2) Интернет запись");
        System.out.print("Ваш выбор: ");
        String choice = in.nextLine();

        if (choice.equals("1")){
            DeleteGameRecord(users);
            in.close();
        }
        if (choice.equals("2")){
            DeleteNetworkRecord(users);
            in.close();
        }
    }
    public static void DeleteGameRecord(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя записи: ");
        String name = in.nextLine();
        System.out.print("Пароль записи: ");
        String password = in.nextLine();


        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }

        if (name.isEmpty() && password.isEmpty()){
            System.out.println("Вы ничего не ввели");
            System.out.println("Удаление прервано");

            in.close();
        }

        if (!name.isEmpty() && !password.isEmpty()){
            List<Game> games = userService.GameSearch(user, name, password);

            if (games.isEmpty()){
                System.out.println("Запись не найдена");
            }
            if (!games.isEmpty()){
                Long temp = null;
                for (Game game1 : games){
                    temp = game1.getId();
                }
                userService.deleteGame(temp);
                System.out.println("Запись удалена");
            }
        }
        in.close();
    }
    public static void DeleteNetworkRecord(List<User> users){
        Scanner in = new Scanner(System.in);

        System.out.print("Имя записи: ");
        String name = in.nextLine();
        System.out.print("Пароль записи: ");
        String password = in.nextLine();


        UserService userService = new UserService();
        User user = null;
        for (User user1 : users){
            user = user1;
        }

        if (name.isEmpty() && password.isEmpty()){
            System.out.println("Вы ничего не ввели");
            System.out.println("Удаление прервано");

            in.close();
        }

        if (!name.isEmpty() && !password.isEmpty()){
            List<Network> networks = userService.NetworkSearch(user, name, password);

            if (networks.isEmpty()){
                System.out.println("Запись не найдена");
            }
            if (!networks.isEmpty()){
                Long temp = null;
                for (Network network : networks){
                    temp = network.getId();
                }
                userService.deleteNetwork(temp);
                System.out.println("Запись удалена");
            }
            in.close();
        }
    }

    public static void Authorisation(){
        Scanner in = new Scanner(System.in);
        System.out.print("Введите логин учетной записи: ");
        String login = in.nextLine();
        System.out.print("Введите пароль учетной записи: ");
        String password = in.nextLine();

        UserService userService = new UserService();
        List<User> users = userService.userSearch(login, password);

        if (users.isEmpty()){
            System.out.println("Учетная запись не найдена");
        }

        if (!users.isEmpty()){
            System.out.println("1) Создать игровую запись");
            System.out.println("2) Создать интернет запись");
            System.out.println("3) Просмотреть все записи");
            System.out.println("4) Редактировать запись");
            System.out.println("5) Удалить запись");
            System.out.println("6) Удалить учетную запись");

            System.out.print("Ваш выбор: ");
            String choice = in.nextLine();

            if (choice.equals("1")){
                AddGame(users);
            }
            if (choice.equals("2")){
                AddNetwork(users);
            }
            if (choice.equals("3")){
                RecordList(users);
            }
            if (choice.equals("4")){
                EditRecord(users);
            }
            if (choice.equals("5")){
                DeleteRecord(users);
            }
            if (choice.equals("exit")) {
                System.out.println("Выход из приложения");
            }
            in.close();
        }
    }

    public static void main(String[] args){
        System.out.println("1) Создать учетную запись");
        System.out.println("2) Войти в учетную запись");

        System.out.print("Ваш выбор: ");
        Scanner in = new Scanner(System.in);
        String choice = in.nextLine();

        if (choice.equals("1")){
            Registry();
        }
        if (choice.equals("2")){
            Authorisation();
        }
        in.close();
    }
}
