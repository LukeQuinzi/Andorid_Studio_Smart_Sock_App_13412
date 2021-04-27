package com.example.smartsock.ui.login;

//Final Means value can't be change
//Private means you can not access the variable

//Credentials Class allowing us to set and get password and username

public class Credentials {
    private String Username;
    private String Password;

    Credentials(String username, String password){

        this.Username = username;
        this.Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
