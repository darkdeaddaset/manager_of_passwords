using Manager_of_passwords.Selecting_passwords;
using Manager_of_passwords.EntityFramework;
using Manager_of_passwords.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace Manager_of_passwords
{
    class MainClass
    {
        public static void Main(string[] args)
        {

            Console.WriteLine("1) Создать учетную запись");
            Console.WriteLine("2) Войти в учетную запись");

            Console.WriteLine();
            ConsoleKeyInfo choice = Console.ReadKey(true);

            do
            {
                //Регистрация
                if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                {
                    Console.Write("Введите имя: ");
                    string? name = Console.ReadLine();
                    Console.Write("Введите пароль: ");
                    string? pass = Console.ReadLine();

                    //Случай, когда имя пользователя нулевое
                    if (string.IsNullOrEmpty(name) && !string.IsNullOrEmpty(pass))
                    {
                        Console.WriteLine("Учетная запись должна иметь логин и пароль");
                    }

                    //Случай, когда пароль пользователя равен нулю
                    if (string.IsNullOrEmpty(pass) && !string.IsNullOrEmpty(name))
                    {
                        Console.WriteLine("Если создать запись без пароля, то ваши записи не в бепозопасности\nВы действительно хотите создать пользователя? Y/n");

                        choice = Console.ReadKey(true);

                        if (choice.Key == ConsoleKey.Y)
                        {
                            using (ApplicationContext context = new ApplicationContext())
                            {
                                name = IGenerateHash.HashString(name);
                                pass = IGenerateHash.HashString(pass);
                                Users users = new Users(name, pass);
                                Console.WriteLine("Хотите создать запись? Y/n");
                                choice = Console.ReadKey(true);

                                if (choice.Key == ConsoleKey.Y)
                                {
                                    Console.WriteLine("Выберите тип создаваемой записи");
                                    Console.WriteLine("1) Создать запись для Интернета");
                                    Console.WriteLine("2) Создать игровую запись");

                                    choice = Console.ReadKey(true);
                                    if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                                    {
                                        Console.Write("Введите имя записи: ");
                                        string name_account = Console.ReadLine();
                                        Console.Write("Введите пароль для записи: ");
                                        string password_account = Console.ReadLine();

                                        //Если пользователь не придумал имя записи
                                        if (string.IsNullOrEmpty(name_account))
                                        {
                                            Console.WriteLine("Запись должна иметь название");
                                        }

                                        //Если пользователь не придумал пароль сам
                                        if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                        {
                                            Console.WriteLine("Генерируем пароль");
                                            Console.Write("Введите количество символов в пароле: ");
                                            int elements = Convert.ToInt32(Console.ReadLine());

                                            choice = Console.ReadKey(true);
                                            do
                                            {
                                                password_account = IGenerate_pass.GeneratePass(elements);
                                                Console.WriteLine(password_account);
                                                Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                choice = Console.ReadKey(true);
                                            }
                                            while (choice.Key == ConsoleKey.N);

                                            if (choice.Key == ConsoleKey.Enter)
                                            {
                                                users.network_Passes.Add(new network_pass(name_account, users.Id, password_account));
                                                context.users.Add(users);
                                                context.SaveChanges();

                                                Console.WriteLine("Интернет запись создана");
                                            }
                                        }

                                        else
                                        {
                                            users.network_Passes.Add(new network_pass(name_account, users.Id, password_account));
                                            context.users.Add(users);
                                            context.SaveChanges();

                                            Console.WriteLine("Интернет запись создана");
                                        }

                                    }

                                    if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                                    {
                                        Console.Write("Введите имя записи: ");
                                        string name_account = Console.ReadLine();
                                        Console.Write("Введите пароль для записи: ");
                                        string password_account = Console.ReadLine();

                                        //Если пользователь не придумал имя записи
                                        if (string.IsNullOrEmpty(name_account))
                                        {
                                            Console.WriteLine("Запись должна иметь название");
                                        }

                                        //Если пользователь не придумал пароль сам
                                        if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                        {
                                            Console.WriteLine("Генерируем пароль");
                                            Console.Write("Введите количество символов в пароле: ");
                                            int elements = Convert.ToInt32(Console.ReadLine());

                                            choice = Console.ReadKey(true);
                                            do
                                            {
                                                password_account = IGenerate_pass.GeneratePass(elements);
                                                Console.WriteLine(password_account);
                                                Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                choice = Console.ReadKey(true);
                                            }
                                            while (choice.Key == ConsoleKey.N);

                                            if (choice.Key == ConsoleKey.Enter)
                                            {
                                                users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                                context.users.Add(users);
                                                context.SaveChanges();

                                                Console.WriteLine("Игровая запись создана");
                                            }
                                        }

                                        else
                                        {
                                            users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                            context.users.Add(users);
                                            context.SaveChanges();

                                            Console.WriteLine("Игровая запись создана");
                                        }
                                    }
                                }

                                //Создание пользователя без учетной записи
                                if (choice.Key == ConsoleKey.N)
                                {
                                    context.users.Add(users);
                                    context.SaveChanges();
                                    Console.WriteLine("Пользователь создан");

                                    Console.WriteLine("Нажмите Enter");
                                    choice = Console.ReadKey(true);
                                }
                            }
                        }

                        //Предлагаем пользователю сгенерить пароль
                        if (choice.Key == ConsoleKey.N)
                        {
                            Console.WriteLine("Сгенерировать пароль? Y/n");
                            choice = Console.ReadKey(true);

                            if (choice.Key == ConsoleKey.Y)
                            {
                                string temp = null;
                                Console.Write("Введите количество символов в пароле: ");
                                int elements = Convert.ToInt32(Console.ReadLine());

                                choice = Console.ReadKey(true);
                                do
                                {
                                    temp = IGenerate_pass.GeneratePass(elements);
                                    Console.WriteLine(temp);
                                    Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                    choice = Console.ReadKey(true);
                                }
                                while (choice.Key == ConsoleKey.N);

                                if (choice.Key == ConsoleKey.Enter)
                                {
                                    using (ApplicationContext context = new ApplicationContext())
                                    {
                                        Users users = new Users(name, temp);
                                        Console.WriteLine("Хотите создать запись? Y/n");
                                        choice = Console.ReadKey(true);

                                        if (choice.Key == ConsoleKey.Y)
                                        {
                                            Console.WriteLine("Выберите тип создаваемой записи");
                                            Console.WriteLine("1) Создать запись для Интернета");
                                            Console.WriteLine("2) Создать игровую запись");

                                            choice = Console.ReadKey(true);
                                            if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                                            {
                                                Console.Write("Введите имя записи: ");
                                                string name_account = Console.ReadLine();
                                                Console.Write("Введите пароль для записи: ");
                                                string password_account = Console.ReadLine();

                                                //Если пользователь не придумал имя записи
                                                if (string.IsNullOrEmpty(name_account))
                                                {
                                                    Console.WriteLine("Запись должна иметь название");
                                                }

                                                //Если пользователь не придумал пароль сам
                                                if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                                {
                                                    Console.WriteLine("Генерируем пароль");
                                                    Console.Write("Введите количество символов в пароле: ");
                                                    int elements1 = Convert.ToInt32(Console.ReadLine());

                                                    choice = Console.ReadKey(true);
                                                    do
                                                    {
                                                        password_account = IGenerate_pass.GeneratePass(elements1);
                                                        Console.WriteLine(password_account);
                                                        Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                        choice = Console.ReadKey(true);
                                                    }
                                                    while (choice.Key == ConsoleKey.N);

                                                    if (choice.Key == ConsoleKey.Enter)
                                                    {
                                                        users.network_Passes.Add(new network_pass(name_account, users.Id, password_account));
                                                        context.users.Add(users);
                                                        context.SaveChanges();

                                                        Console.WriteLine("Интернет запись создана");
                                                    }
                                                }

                                            }

                                            if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                                            {
                                                Console.Write("Введите имя записи: ");
                                                string name_account = Console.ReadLine();
                                                Console.Write("Введите пароль для записи: ");
                                                string password_account = Console.ReadLine();

                                                //Если пользователь не придумал имя записи
                                                if (string.IsNullOrEmpty(name_account))
                                                {
                                                    Console.WriteLine("Запись должна иметь название");
                                                }

                                                //Если пользователь не придумал пароль сам
                                                if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                                {
                                                    Console.WriteLine("Генерируем пароль");
                                                    Console.Write("Введите количество символов в пароле: ");
                                                    int elements1 = Convert.ToInt32(Console.ReadLine());

                                                    choice = Console.ReadKey(true);
                                                    do
                                                    {
                                                        password_account = IGenerate_pass.GeneratePass(elements1);
                                                        Console.WriteLine(password_account);
                                                        Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                        choice = Console.ReadKey(true);
                                                    }
                                                    while (choice.Key == ConsoleKey.N);

                                                    if (choice.Key == ConsoleKey.Enter)
                                                    {
                                                        users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                                        context.users.Add(users);
                                                        context.SaveChanges();

                                                        Console.WriteLine("Интернет запись создана");
                                                    }
                                                }

                                                else
                                                {
                                                    users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                                    context.users.Add(users);
                                                    context.SaveChanges();

                                                    Console.WriteLine("Игровая запись создана");
                                                }
                                            }
                                        }

                                        if (choice.Key == ConsoleKey.N)
                                        {
                                            context.users.Add(users);
                                            context.SaveChanges();
                                            Console.WriteLine("Пользователь создан");

                                            Console.WriteLine("Нажмите Enter");
                                            choice = Console.ReadKey(true);
                                        }
                                    }
                                }
                            }

                            if (choice.Key == ConsoleKey.N)
                            {
                                using (ApplicationContext context = new ApplicationContext())
                                {
                                    Users users = new Users(name, pass);
                                    context.users.Add(users);
                                    context.SaveChanges();

                                    Console.WriteLine("Пользователь создан");
                                }
                            }
                        }
                    }

                    //Случай, когда имя пользователя и его пароль не нулевые
                    if (!string.IsNullOrEmpty(name) && !string.IsNullOrEmpty(pass))
                    {
                        using (ApplicationContext context = new ApplicationContext())
                        {
                            name = IGenerateHash.HashString(name);
                            pass = IGenerateHash.HashString(pass);
                            Users users = new Users(name, pass);

                            Console.WriteLine("Хотите создать запись? Y/n");
                            choice = Console.ReadKey(true);

                            if (choice.Key == ConsoleKey.Y)
                            {
                                Console.WriteLine("Выберите тип создаваемой записи");
                                Console.WriteLine("1) Создать запись для Интернета");
                                Console.WriteLine("2) Создать игровую запись");

                                choice = Console.ReadKey(true);
                                if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                                {
                                    Console.Write("Введите имя записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите пароль для записи: ");
                                    string password_account = Console.ReadLine();

                                    //Если имя записи нулевые
                                    if (string.IsNullOrEmpty(name_account))
                                    {
                                        Console.WriteLine("Запись должна иметь название");
                                    }

                                    //Если пользователь не придумал пароль сам
                                    if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                    {
                                        Console.WriteLine("Генерируем пароль");
                                        Console.Write("Введите количество символов в пароле: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        choice = Console.ReadKey(true);
                                        do
                                        {
                                            password_account = IGenerate_pass.GeneratePass(elements);
                                            Console.WriteLine(password_account);
                                            Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                            choice = Console.ReadKey(true);
                                        }
                                        while (choice.Key == ConsoleKey.N);

                                        if (choice.Key == ConsoleKey.Enter)
                                        {
                                            users.network_Passes.Add(new network_pass(name_account, users.Id, password_account));
                                            context.users.Add(users);
                                            context.SaveChanges();

                                            Console.WriteLine("Интернет запись создана");
                                        }
                                    }

                                    else
                                    {
                                        users.network_Passes.Add(new network_pass(name_account, users.Id, password_account));
                                        context.users.Add(users);
                                        context.SaveChanges();

                                        Console.WriteLine("Интернет запись создана");
                                    }

                                }

                                if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                                {
                                    Console.Write("Введите имя записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите пароль для записи: ");
                                    string password_account = Console.ReadLine();

                                    //Если пользователь не придумал имя записи
                                    if (string.IsNullOrEmpty(name_account))
                                    {
                                        Console.WriteLine("Запись должна иметь название");
                                    }

                                    //Если пользователь не придумал пароль сам
                                    if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(password_account))
                                    {
                                        Console.WriteLine("Генерируем пароль");
                                        Console.Write("Введите количество символов в пароле: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        choice = Console.ReadKey(true);
                                        do
                                        {
                                            password_account = IGenerate_pass.GeneratePass(elements);
                                            Console.WriteLine(password_account);
                                            Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                            choice = Console.ReadKey(true);
                                        }
                                        while (choice.Key == ConsoleKey.N);

                                        if (choice.Key == ConsoleKey.Enter)
                                        {
                                            users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                            context.users.Add(users);
                                            context.SaveChanges();

                                            Console.WriteLine("Игровая запись создана");
                                        }
                                    }

                                    else
                                    {
                                        users.game_Passes.Add(new game_pass(name_account, users.Id, password_account));
                                        context.users.Add(users);
                                        context.SaveChanges();

                                        Console.WriteLine("Игровая запись создана");
                                    }
                                }
                            }

                            if (choice.Key == ConsoleKey.N)
                            {
                                context.users.Add(users);
                                context.SaveChanges();

                                Console.WriteLine("Пользователь создан");
                            }
                        }
                    }

                    //Случай, когда ничего не ввел
                    if (string.IsNullOrEmpty(name) && string.IsNullOrEmpty(pass))
                    {
                        Console.WriteLine("Пользователь не создан");
                    }
                }

                //Авторизация и работа с записями
                if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                {
                    Console.Write("Введите логин: ");
                    string name = Console.ReadLine();
                    Console.Write("Введите пароль: ");
                    string? pass = Console.ReadLine();

                    name = IGenerateHash.HashString(name);
                    pass = IGenerateHash.HashString(pass);
                    bool check = ICheck_Account.Check_Account(name, pass);

                    //Пользован авторизован
                    if (check == true)
                    {
                        Console.WriteLine();
                        Console.WriteLine("Приложение: Менеджер паролей");
                        Console.WriteLine("Выберите тип создаваемой записи");
                        Console.WriteLine("1) Создать запись для Интернета");
                        Console.WriteLine("2) Создать игровую запись");
                        Console.WriteLine("3) Просмотреть список всех записей");
                        Console.WriteLine("4) Редактировать запись");
                        Console.WriteLine("5) Удалить запись");

                        choice = Console.ReadKey(true);
                        //Создание интернет записи
                        if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                        {
                            choice = Console.ReadKey(true);
                            do
                            {
                                Console.WriteLine();
                                Console.WriteLine("Вы создаете интернет запись");
                                Console.Write("Введите название записи: ");
                                string? name_account = Console.ReadLine();
                                Console.Write("Введите пароль: ");
                                string? pass_account = Console.ReadLine();

                                //Попытка создания интернет записи без названия и пароля
                                if (string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(pass_account))
                                {
                                    try
                                    {
                                        using (ApplicationContext context = new ApplicationContext())
                                        {
                                            network_pass network_Pass = new network_pass();
                                        }

                                    }
                                    catch (Exception ex)
                                    {
                                        Console.WriteLine(ex.ToString());
                                    }
                                }

                                //Создание интернет записи с названием, но без пароля
                                if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(pass_account))
                                {
                                    Console.WriteLine("Вы создаете запись без пароля.\nСгенерировать пароль? Y/n: ");
                                    choice = Console.ReadKey(true);

                                    if (choice.Key == ConsoleKey.Y)
                                    {
                                        Console.Write("Введите количетсво элементов для пароля: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        using (ApplicationContext context = new ApplicationContext())
                                        {
                                            int user_id = 0;
                                            var user = context.users
                                                .Where(u => u.Name == name)
                                                .Where(u => u.Password == pass);
                                            foreach (Users users in user)
                                            {
                                                user_id = users.Id;
                                            }

                                            network_pass network_Pass = new network_pass(name_account, user_id);
                                            string? new_pass = null;
                                            //Console.WriteLine("Нажмите клавишу N для генерации пароля");

                                            choice = Console.ReadKey(true);
                                            do
                                            {
                                                new_pass = IGenerate_pass.GeneratePass(elements);
                                                Console.WriteLine(new_pass);
                                                Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                choice = Console.ReadKey(true);
                                            }
                                            while (choice.Key == ConsoleKey.N);

                                            if (choice.Key == ConsoleKey.Enter)
                                            {
                                                network_Pass.setPassword(new_pass);
                                                network_Pass.getPassword();
                                                Console.WriteLine("Пароль установлен");

                                                context.net_pass.Add(network_Pass);
                                                context.SaveChanges();
                                                Console.WriteLine("Запись сохранена");
                                            }

                                        }
                                    }
                                    if (choice.Key == ConsoleKey.N)
                                    {
                                        Console.WriteLine("Вы создали учетную запись без пароля");
                                    }
                                }

                                //Создание интернет записи с именем и паролем
                                if (!string.IsNullOrEmpty(name_account) && !string.IsNullOrEmpty(pass_account))
                                {
                                    using (ApplicationContext context = new ApplicationContext())
                                    {
                                        int user_id = 0;
                                        var user = context.users
                                            .Where(u => u.Name == name)
                                            .Where(u => u.Password == pass);
                                        foreach (Users users in user)
                                        {
                                            user_id = users.Id;
                                        }

                                        network_pass network_Pass = new network_pass(name_account, user_id, pass_account);

                                        context.net_pass.Add(network_Pass);
                                        context.SaveChanges();
                                        Console.WriteLine("Запись создана");
                                    }


                                }

                                choice = Console.ReadKey(true);
                            }
                            while (choice.Key == ConsoleKey.Enter);

                            if (choice.Key == ConsoleKey.Escape)
                            {
                                Console.WriteLine("Выход из создания интернет записи");
                            }
                        }

                        //Создание игровой записи
                        if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                        {
                            choice = Console.ReadKey(true);
                            do
                            {
                                Console.WriteLine();
                                Console.WriteLine("Вы создаете игровую запись");
                                Console.Write("Введите название записи: ");
                                string? name_account = Console.ReadLine();
                                Console.Write("Введите пароль: ");
                                string? pass_account = Console.ReadLine();

                                //Создание пустой игровой записи
                                if (string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(pass_account))
                                {
                                    Console.WriteLine("Нельзя создать пустую запись");
                                }

                                //Создание игровой с именем, но без пароля
                                if (!string.IsNullOrEmpty(name_account) && string.IsNullOrEmpty(pass_account))
                                {
                                    Console.WriteLine("Вы создаете запись без пароля.\nСгенерировать пароль? Y/n: ");
                                    choice = Console.ReadKey(true);

                                    if (choice.Key == ConsoleKey.Y)
                                    {
                                        Console.Write("Введите количетсво элементов для пароля: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        using (ApplicationContext context = new ApplicationContext())
                                        {
                                            int user_id = 0;
                                            var user = context.users
                                                .Where(u => u.Name == name)
                                                .Where(u => u.Password == pass);
                                            foreach (Users users in user)
                                            {
                                                user_id = users.Id;
                                            }

                                            game_pass game_Pass = new game_pass(name_account, user_id);
                                            string? new_pass = null;

                                            choice = Console.ReadKey(true);
                                            do
                                            {
                                                new_pass = IGenerate_pass.GeneratePass(elements);
                                                Console.WriteLine(new_pass);
                                                Console.WriteLine("Выбрать данный пароль, паролем для учетной записи? Enter/n");

                                                choice = Console.ReadKey(true);
                                            }
                                            while (choice.Key == ConsoleKey.N);

                                            if (choice.Key == ConsoleKey.Enter)
                                            {
                                                game_Pass.setPassword(new_pass);
                                                game_Pass.getPassword();
                                                Console.WriteLine("Пароль установлен");

                                                context.game_pass.Add(game_Pass);
                                                context.SaveChanges();
                                                Console.WriteLine("Запись сохранена");
                                            }
                                        }

                                    }
                                    if (choice.Key == ConsoleKey.N)
                                    {
                                        Console.WriteLine("Вы создали учетную запись без пароля");
                                    }

                                }

                                //Создание игровой записи с именем и паролем
                                if (!string.IsNullOrEmpty(name_account) && !string.IsNullOrEmpty(pass_account))
                                {
                                    using (ApplicationContext context = new ApplicationContext())
                                    {
                                        int user_id = 0;
                                        var user = context.users
                                            .Where(u => u.Name == name)
                                            .Where(u => u.Password == pass);
                                        foreach (Users users in user)
                                        {
                                            user_id = users.Id;
                                        }

                                        game_pass game_Pass = new game_pass(name_account, user_id, pass_account);

                                        context.game_pass.Add(game_Pass);
                                        context.SaveChanges();

                                        Console.WriteLine("Запись сохранена");
                                    }
                                }

                                choice = Console.ReadKey(true);
                            }
                            while (choice.Key == ConsoleKey.Enter);

                            if (choice.Key == ConsoleKey.Escape)
                            {
                                Console.WriteLine("Выход из создания игровой записи");
                            }
                        }

                        //Вывод всех записей с паролями
                        if (choice.Key == ConsoleKey.D3 || choice.Key == ConsoleKey.NumPad3)
                        {

                            using (ApplicationContext context = new ApplicationContext())
                            {
                                var all_net = context.net_pass
                                    .Include(u => u.user)
                                    .Where(u => u.user.Name == name)
                                    .Where(u => u.user.Password == pass)
                                    .Where(u => u.user_id == u.user.Id);
                                Console.WriteLine();
                                Console.WriteLine("Список интернет записей");

                                foreach (var net_pass in all_net)
                                {
                                    Console.WriteLine("Name: " + net_pass.name_network + " Password: " + net_pass.password);
                                }

                                var all_game = context.game_pass
                                    .Include(u => u.user)
                                    .Where(u => u.user.Name == name)
                                    .Where(u => u.user.Password == pass)
                                    .Where(u => u.user_id == u.user.Id);
                                Console.WriteLine();

                                Console.WriteLine("Список игровых записей");

                                foreach (var game_pass in all_game)
                                {
                                    Console.WriteLine("Name: " + game_pass.name_game + " Password: " + game_pass.password);
                                }
                            }
                        }

                        //Редактирование записи
                        if (choice.Key == ConsoleKey.D4 || choice.Key == ConsoleKey.NumPad4)
                        {
                            Console.WriteLine("1) Изменить название записи");
                            Console.WriteLine("2) Изменить пароль");

                            choice = Console.ReadKey(true);

                            //Изменение имени
                            if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                            {
                                Console.WriteLine("1) Интернет запись");
                                Console.WriteLine("2) Игровая запись");

                                choice = Console.ReadKey(true);
                                if ((choice.Key == ConsoleKey.D1) || choice.Key == ConsoleKey.NumPad1)
                                {
                                    Console.Write("Введите название записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите новое название записи: ");
                                    string change_name = Console.ReadLine();

                                    using (ApplicationContext context = new ApplicationContext())
                                    {
                                        int change = context.Database.ExecuteSqlRaw("UPDATE net_pass SET name_network={0} WHERE name_network={1}", change_name, name_account);
                                        context.SaveChanges();
                                        Console.WriteLine("Изменения внесены");
                                    }

                                    choice = Console.ReadKey(true);
                                }

                                if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                                {
                                    Console.Write("Введите название записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите новое название записи: ");
                                    string change_name = Console.ReadLine();

                                    using (ApplicationContext context = new ApplicationContext())
                                    {
                                        int change = context.Database.ExecuteSqlRaw("UPDATE game_pass SET name_game={0} WHERE name_game={1}", change_name, name_account);
                                        context.SaveChanges();
                                        Console.WriteLine("Изменения внесены");
                                    }

                                    choice = Console.ReadKey(true);
                                }
                            }

                            //Изменение пароля
                            if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                            {
                                Console.WriteLine("1) Интернет запись");
                                Console.WriteLine("2) Игровая запись");

                                choice = Console.ReadKey(true);


                                if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                                {
                                    Console.Write("Введите название записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите новый пароль для записи: ");
                                    string change_password = Console.ReadLine();

                                    //Если пароль меняется на пустой пароль
                                    if (string.IsNullOrEmpty(change_password))
                                    {
                                        Console.WriteLine("Вы изменяете существующий пароль на пустой");
                                        Console.WriteLine("Пароль будет сгенерирован");
                                        Console.Write("Введите количество символов в пароле: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        choice = Console.ReadKey(true);
                                        do
                                        {
                                            change_password = IGenerate_pass.GeneratePass(elements);
                                            Console.WriteLine($"Сгенерированный пароль: {change_password}");
                                            Console.WriteLine("Нажмите Enter, чтобы сохранить сгенерированный пароль");
                                            Console.WriteLine("Нажмите N, чтобы сгенерировать новый пароль");

                                            choice = Console.ReadKey(true);
                                        }
                                        while (choice.Key == ConsoleKey.N);
                                    }

                                    //Если пароль меняется не на пустой
                                    if (!string.IsNullOrEmpty(change_password))
                                    {
                                        using (ApplicationContext context = new ApplicationContext())
                                        {
                                            int change = context.Database.ExecuteSqlRaw("UPDATE net_pass SET password={0} WHERE name_network={1}", change_password, name_account);
                                            context.SaveChanges();
                                            Console.WriteLine("Изменения внесены");
                                        }
                                    }
                                    choice = Console.ReadKey(true);
                                }


                                if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                                {
                                    Console.Write("Введите название записи: ");
                                    string name_account = Console.ReadLine();
                                    Console.Write("Введите новый пароль для записи: ");
                                    string change_password = Console.ReadLine();

                                    //Если пароль меняется на пустой пароль
                                    if (string.IsNullOrEmpty(change_password))
                                    {
                                        Console.WriteLine("Вы изменяете существующий пароль на пустой");
                                        Console.WriteLine("Пароль будет сгенерирован");
                                        Console.Write("Введите количество символов в пароле: ");
                                        int elements = Convert.ToInt32(Console.ReadLine());

                                        choice = Console.ReadKey(true);
                                        do
                                        {
                                            change_password = IGenerate_pass.GeneratePass(elements);
                                            Console.WriteLine($"Сгенерированный пароль: {change_password}");
                                            Console.WriteLine("Нажмите Enter, чтобы сохранить сгенерированный пароль");
                                            Console.WriteLine("Нажмите N, чтобы сгенерировать новый пароль");

                                            choice = Console.ReadKey(true);
                                        }
                                        while (choice.Key == ConsoleKey.N);
                                    }

                                    //Если пароль меняется не на пустой
                                    if (!string.IsNullOrEmpty(change_password))
                                    {
                                        using (ApplicationContext context = new ApplicationContext())
                                        {
                                            int change = context.Database.ExecuteSqlRaw("UPDATE game_pass SET password={0} WHERE name_network={1}", change_password, name_account);
                                            context.SaveChanges();
                                            Console.WriteLine("Изменения внесены");
                                        }
                                    }
                                    choice = Console.ReadKey(true);
                                }                                
                            }
                        }

                        //Удаление записи (полностью)
                        if (choice.Key == ConsoleKey.D5 || choice.Key == ConsoleKey.NumPad5)
                        {
                            Console.WriteLine("1) Интернет запись");
                            Console.WriteLine("2) Игровая запись");

                            choice = Console.ReadKey(true);

                            if (choice.Key == ConsoleKey.D1 || choice.Key == ConsoleKey.NumPad1)
                            {
                                Console.Write("Введите название удаляемой записи: ");
                                string delete_account = Console.ReadLine();

                                using (ApplicationContext context = new ApplicationContext())
                                {
                                    int deleted = context.Database.ExecuteSqlRaw("DELETE FROM net_pass WHERE name_network={0}", delete_account);
                                    context.SaveChanges();
                                    Console.WriteLine("Запись удалена");
                                }
                            }

                            if (choice.Key == ConsoleKey.D2 || choice.Key == ConsoleKey.NumPad2)
                            {
                                Console.Write("Введите название удаляемой записи: ");
                                string delete_account = Console.ReadLine();

                                using (ApplicationContext context = new ApplicationContext())
                                {
                                    int deleted = context.Database.ExecuteSqlRaw("DELETE FROM game_pass WHERE name_game={0}", delete_account);
                                    context.SaveChanges();
                                    Console.WriteLine("Запись удалена");
                                }
                            }
                        }

                        //Если пользователь не зарегистрирован
                        if (check == false)
                        {
                            Console.WriteLine("Пользователь не найден");
                        }
                    }
                    choice = Console.ReadKey(true);
                }                
                if (choice.Key == ConsoleKey.Escape)
                { }
            } while (choice.Key == ConsoleKey.Z);
        }
    }
}
