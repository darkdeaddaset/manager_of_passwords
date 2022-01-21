package model;

import exceptions.ExceptionNotPassword;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Game> games;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Network> networks;

    public User(){}

    public User(String login) throws ExceptionNotPassword {
        throw new ExceptionNotPassword("Вы создаете учетную запись без пароля");
    }

    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.games = new ArrayList<>();
        this.networks = new ArrayList<>();
    }

    public void setLogin(String login){
        this.login = login;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public String getLogin(){
        return login;
    }

    public String getPassword(){
        return password;
    }

    public List<Game> getGames(){
        return games;
    }

    public List<Network> getNetworks(){
        return networks;
    }

    public void setGames(List<Game> games){
        this.games = games;
    }

    public void setNetworks(List<Network> networks){
        this.networks = networks;
    }

    public void addGame(Game game){
        game.setUser(this);
        games.add(game);
    }

    public void addNetwork(Network network){
        network.setUser(this);
        networks.add(network);
    }
}
