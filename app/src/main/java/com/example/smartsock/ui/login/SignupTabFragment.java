package com.example.smartsock.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartsock.MainActivity;
import com.example.smartsock.R;


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
    public static Credentials credentials;

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
        Gender = view.findViewById(R.id.gender);
        DOB = view.findViewById(R.id.DOB);
        Signup_button = view.findViewById(R.id.signup);


        Signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sign_username = Sign_username.getText().toString();
                String sign_password = Sign_password.getText().toString();
                String sign_confirm_password = Sign_confirm_password.getText().toString();


                if(validate(sign_username, sign_password,sign_confirm_password)){
                    credentials = new Credentials(sign_username, sign_password);
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                    Toast.makeText(getContext(),"Signup Successful", Toast.LENGTH_SHORT).show();



                }

            }
        });
/*
        email.setTranslationX(800);
        username.setTranslationX(800);
        phone.setTranslationX(800);
        password.setTranslationX(800);
        confirm_password.setTranslationX(800);
        gender.setTranslationX(800);
        DOB.setTranslationX(800);
        signup.setTranslationX(800);

        email.setAlpha(v);
        username.setAlpha(v);
        phone.setAlpha(v);
        password.setAlpha(v);
        confirm_password.setAlpha(v);
        gender.setAlpha(v);
        DOB.setAlpha(v);
        signup.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        username.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        phone.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        confirm_password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        gender.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        DOB.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        signup.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
*/
/*

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gets whatever is input in the username field and converts the text into a string
                signinusername = username.getText().toString();
                signinpassword = password.getText().toString();

                editor.putString("signinusername",signinusername);
                editor.putString("signinpassword",signinpassword);
                editor.apply();
                Toast.makeText(getContext(),"Registered", Toast.LENGTH_SHORT).show();

            }

        });*/


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