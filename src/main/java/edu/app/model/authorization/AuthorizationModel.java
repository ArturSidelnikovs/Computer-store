package edu.app.model.authorization;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AuthorizationModel implements Serializable {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String login;
    @NotBlank
    private String password;


    public static AuthorizationModel fromLogin(String username) {
        AuthorizationModel newUser = new AuthorizationModel();
        newUser.setFirstName(username);
        newUser.setLastName(username);
        newUser.setLogin(username);
        newUser.setPassword(username);
        return newUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
