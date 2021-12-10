using Manager_of_passwords.Interfaces;
using System.ComponentModel.DataAnnotations.Schema;

namespace Manager_of_passwords.Selecting_passwords
{
    public class game_pass : IGenerate_pass
    {
        public int Id_game { get; set; }
        public string name_game { get; set; }
        public string password { get; set; }
        public int user_id { get; set; }
        public Users user { get; set; }

        public game_pass()
        {
            //throw new Exception("Для создания игровой записи необходимо ввести пароль и название записи\nЗапись не создана");
        }

        public game_pass(string name_game, int user_id) : this(name_game, user_id, string.Empty) 
        {

        }

        public game_pass(string name_game, int user_id, string password)
        {
            this.name_game = name_game;
            this.password = password;
            this.user_id = user_id;
        }

        public void getPassword()
        {
            Console.WriteLine(password);
        }

        public void getName()
        {
            Console.WriteLine(name_game);
        }

        internal void setPassword(string pass)
        {
            password = pass;
        }
    }
}
