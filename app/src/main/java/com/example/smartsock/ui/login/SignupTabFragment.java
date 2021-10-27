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
        Gender = view.findViewById(R.id.signup_gender);
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
                String email = Email.getText().toString();
                String phone = Phone.getText().toString();
                String dob = DOB.getText().toString();
                String gender = Gender.getText().toString();

                if(validate(sign_username, sign_password,sign_confirm_password)){

                    if (credentials.checkUsername(sign_username)) {
                        Toast.makeText(getContext(),"Username already in use!", Toast.LENGTH_SHORT).show();

                    }else {
                        //Only if the username is unique we add the credentials to the map and register the user
                        credentials.addCredentials(sign_username,sign_password,email,phone,dob,gender);

                        /* Store Credentials */
                        // Credentials are stored in location Device File Explorer >> data >> data >> com.example.smartsock >> shared_prefs >> CredentialsDB
                        sharedPreferencesEditor.putString(sign_username, sign_password);
                        sharedPreferencesEditor.putString("SavedUsername", sign_username);
                        sharedPreferencesEditor.putString("SavedPassword", sign_password);
                        sharedPreferencesEditor.putString("SavedEmail", email);
                        sharedPreferencesEditor.putString("SavedPhone", phone);
                        sharedPreferencesEditor.putString("SavedDOB", dob);
                        sharedPreferencesEditor.putString("SavedGender", gender);

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

    /*
     The validate function takes in parameters on the signup page of the app and decides whether
     those inputs are suitable for a user to sign up with. For example, if a password doesn't
     match the confirm password input then the user will be prompted with a message at the bottom
     of the phone and doesn't let the user sign in (the function returns false. If all the
     parameters ar met the function will return true and the users details will be stored and an
     account will be made.
    */

    private boolean validate(String username, String password, String confirm_password){
        if(username.isEmpty() && password.isEmpty() && confirm_password.isEmpty()) {
            Toast.makeText(getContext(), "Please fill in the username, password and confirm password fields at least", Toast.LENGTH_LONG).show();
            return false;
        }

        if (password.length() < 8 && !password.equals("")) {
            Toast.makeText(getContext(), "Password should be at least 8 characters", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!password.equals(confirm_password)){
            Toast.makeText(getContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty() || confirm_password.isEmpty()){
            Toast.makeText(getContext(), "Please enter a password and confirm the password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}