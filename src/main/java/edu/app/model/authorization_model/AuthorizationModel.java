package edu.app.model.authorization_model;

import javax.validation.constraints.NotBlank;

public class AuthorizationModel {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

    public static AuthorizationModel fromLogin(String username) {
        AuthorizationModel newUser = new AuthorizationModel();
        newUser.setFirstName(username);
        newUser.setLastName(username);
        newUser.setLogin(username);
        newUser.setPassword(username);
        newUser.setRepeatPassword(username);
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

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
