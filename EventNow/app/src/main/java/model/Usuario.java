package model;

/**
 * Created by rodrigo on 12/05/15.
 */
public class Usuario {

    private String login;
    private String email;
    private String password;

    //Class for create a new data user on bank.

    public Usuario(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {return  login;}
    public void setLogin(String login) {this.login = login;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

}

