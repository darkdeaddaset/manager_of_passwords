using Microsoft.EntityFrameworkCore;
using Manager_of_passwords.Selecting_passwords;

namespace Manager_of_passwords.EntityFramework
{
    public class ApplicationContext : DbContext
    {
        public DbSet<Users> ?Users { get; set; }
        public DbSet<network_pass> ?Net_Pass { get; set; }
        public DbSet<game_pass> ?Game_Pass { get; set; }

        public ApplicationContext()
        {
            //Database.EnsureDeleted();
            Database.EnsureCreated();
        }
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseNpgsql("Host=localhost;Port=5432;Database=manager;Username=postgres;Password=lbvfbpflf");
        }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Users>().HasKey(u => u.Id);
            modelBuilder.Entity<Users>()
                .Property(u => u.Name).IsRequired();                

            modelBuilder.Entity<network_pass>().HasKey(u => u.Id_network);
            modelBuilder.Entity<network_pass>()
                .HasOne(u => u.user)
                .WithMany(u => u.network_Passes)
                .HasForeignKey(u => u.user_id);

            modelBuilder.Entity<game_pass>().HasKey(u => u.Id_game);
            modelBuilder.Entity<game_pass>()
                .HasOne(u => u.user)
                .WithMany(u => u.game_Passes)
                .HasForeignKey(u => u.user_id);
        }
    }
}
