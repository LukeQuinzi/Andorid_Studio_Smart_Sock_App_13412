package com.example.smartsock.ui.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.smartsock.R;

import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class SignupTabFragment extends Fragment {
    private EditText Email;
    private EditText Sign_username;
    private EditText Phone;
    private EditText Sign_password;
    private EditText Sign_confirm_password;
    private EditText Gender;
    private EditText DOB;
    private Button Signup_button;

    float v=0;
    public Credentials credentials;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);


        Email = view.findViewById(R.id.signup_email);
        Sign_username = view.findViewById(R.id.signup_username);
        Phone = view.findViewById(R.id.signup_phone);
        Sign_password = view.findViewById(R.id.signup_password);
        Sign_confirm_password = view.findViewById(R.id.signup_confirm_password);
        DOB = view.findViewById(R.id.DOB);
        Signup_button = view.findViewById(R.id.signup);

        credentials = new Credentials();

        sharedPreferences = getContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if(preferencesMap.size() != 0){
                credentials.loadCredentials(preferencesMap);
            }
        }


        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sign_username = Sign_username.getText().toString();
                String sign_password = Sign_password.getText().toString();
                String sign_confirm_password = Sign_confirm_password.getText().toString();


                if(validate(sign_username, sign_password,sign_confirm_password)){

                    if (credentials.checkUsername(sign_username)) {
                        Toast.makeText(getContext(),"Username already in use!", Toast.LENGTH_SHORT).show();

                    }else {
                        //Only if the username is unique we add the credentials to the map and register the user
                        credentials.addCredentials(sign_username, sign_password);

                        /* Store Credentials */
                        // Credentials are stores in location Device File Explorer >> data >> data >> com.example.smartsock >> CredentialsDB
                        sharedPreferencesEditor.putString(sign_username, sign_password);

                        sharedPreferencesEditor.putString("LastSavedUsername", sign_username);
                        sharedPreferencesEditor.putString("LastSavedPassword", sign_password);

                        /* Commits the changes and adds them to the file */
                        sharedPreferencesEditor.apply();


                        Intent intent = new Intent();
                        intent.setClass(getActivity(), LoginActivity.class);
                        getActivity().startActivity(intent);
                        Toast.makeText(getContext(),"Signup Successful", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


        return view;
    }

    private boolean validate(String username, String password, String confirm_password){
        if(username.isEmpty() || password.length() < 8 && password != confirm_password) {
            Toast.makeText(getContext(),"Enter all details, Password should be at least 8 characters, Ensure Confirm Passwords is correct", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}