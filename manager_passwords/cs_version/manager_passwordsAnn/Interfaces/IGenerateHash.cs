using System.Security.Cryptography;
using System.Text;

namespace Manager_of_passwords.Interfaces
{
    internal interface IGenerateHash
    {
        public static string HashString(string name)
        {
            byte[] name_bytes = Encoding.Unicode.GetBytes(name);

            SHA512 sHA512 = SHA512.Create();
            byte[] hash_name = sHA512.ComputeHash(name_bytes);

            string new_name = string.Empty;

            foreach (byte b in hash_name)
            {
                new_name += string.Format("{0:x2}", b);
            }
            return new_name;            
        }
    }
}
