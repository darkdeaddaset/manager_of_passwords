using System.Text;

namespace Manager_of_passwords.Interfaces
{
    internal interface IGenerate_pass
    {        
        static string GeneratePass(int length)
        {
            string word_small = "abcdefghijklmnopqrstuvwsyz";
            string word_large = word_small.ToUpper();
            string symbols = "!@#$%^&*()_-+=?;:'[]{}";
            string numbers = "1234567890";

            Random random = new Random();
            StringBuilder result = new StringBuilder(length);
            String temp = String.Empty;

            temp += word_small + word_large + symbols + numbers;
            for (int i = 0; i < length; i++)
            {
                result.Append(temp[random.Next(0, temp.Length)]);
            }
            return result.ToString();
        }
    }
}
