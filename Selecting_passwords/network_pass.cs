using Manager_of_passwords.Interfaces;

namespace Manager_of_passwords.Selecting_passwords
{
    public class network_pass : IGenerate_pass
    {
        public int Id_network { get; set; }
        public string name_network { get; set; }
        public string password { get; set; }
        public int user_id { get; set; }
        public Users user { get; set; }

        public network_pass()
        {
            //throw new Exception("Для создания записи необходимо ввести пароль и название записи\nЗапись не создана");
        }

        public network_pass(string name, int user_id) : this(name, user_id, string.Empty)
        {

        }

        public network_pass(string name_network, int user_id, string password)
        {
            this.name_network = name_network;
            this.password = password;
            this.user_id = user_id;
        }

        public string getPassword()
        {
            return password;
        }

        public string getName()
        {
            return name_network;
        }

        internal void setPassword(string pass)
        {
            password = pass;
        }
    }
}
