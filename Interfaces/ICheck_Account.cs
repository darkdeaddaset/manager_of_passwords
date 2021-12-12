using Manager_of_passwords.EntityFramework;

namespace Manager_of_passwords.Interfaces
{
    internal interface ICheck_Account
    {
        static bool Check_Account(string name, string pass)
        {
            using (ApplicationContext context = new ApplicationContext())
            {
                string name_check = null;
                string pass_check = null;

                var user = context.users
                    .Where(u => u.Name == name)
                    .Where(u => u.Password == pass);

                foreach (var users in user)
                {
                    name_check = users.Name;
                    pass_check = users.Password;
                }

                if (name_check != null && pass_check != null)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }
}
