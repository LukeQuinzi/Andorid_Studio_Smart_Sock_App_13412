package com.example.smartsock.ui.login;

//Final Means value can't be change
//Private means you can not access the variable

//Credentials Class allowing us to set and get password and username

import java.util.HashMap;
import java.util.Map;

public class Credentials {
    HashMap<String, String> credentialsMapper = new HashMap<String, String>();

    public void addCredentials(String sign_username, String sign_password, String email, String phone, String username, String password){
        credentialsMapper.put(username,password);

    }

    public boolean checkUsername(String username){
        return credentialsMapper.containsKey(username);
    }

    public boolean verifyCredentials(String username, String password){

        // checks if user name exists
        if(credentialsMapper.containsKey(username)){
            //check if password matches with the username
            if(password.equals(credentialsMapper.get(username))){
                return true;
            }
        }

        return false;
    }

    public void loadCredentials(Map<String, ?> preferencesMap){
        for(Map.Entry<String, ?> entries : preferencesMap.entrySet()){
            // If entries are not equal to the remember me checkbox we come here
            if(!entries.getKey().equals("RememberMeCheckbox")){
                credentialsMapper.put(entries.getKey(), entries.getValue().toString());

            }
        }
    }
}
