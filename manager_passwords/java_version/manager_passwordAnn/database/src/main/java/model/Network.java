package model;

import exceptions.ExceptionNotPassword;
import exceptions.ExceptionNotUrl;

import javax.persistence.*;

@Entity
@Table(name = "networks")
public class Network {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "url")
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Network(){}

    public Network(String name) throws ExceptionNotPassword {
        throw new ExceptionNotPassword("Вы создаете запись без пароля");
    }

    public Network(String name, String password) throws ExceptionNotUrl {
        throw new ExceptionNotUrl("Вы создаете запись без ссылки");
    }

    public Network(String name, String password, String url){
        this.name = name;
        this.password = password;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }
}
