using Manager_of_passwords.Interfaces;
using Manager_of_passwords.Selecting_passwords;

namespace Manager_of_passwords
{
    public class Users : IGenerate_pass, IGenerateHash
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string? Password { get; set; }

        public List<game_pass> game_Passes { get; set; } = new List<game_pass>();
        public List<network_pass> network_Passes { get; set; } = new List<network_pass> { };

        public Users()
        {

        }
        public Users(string pass)
        {
            throw new Exception("Необходимо придумать логин для пользователя");
        }
        public Users(string name, string pass)
        {
            this.Name = name;
            this.Password = pass;
        }
    }
}
