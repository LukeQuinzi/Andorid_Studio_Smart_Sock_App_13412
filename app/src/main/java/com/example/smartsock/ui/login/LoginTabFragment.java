package com.example.smartsock.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartsock.MainActivity;
import com.example.smartsock.R;

import static android.content.Context.MODE_PRIVATE;

public class LoginTabFragment extends Fragment {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;
    CheckBox Rememberme;

    float v = 0;
    boolean isValid = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.login);
        Rememberme = view.findViewById(R.id.CBRememberme);


        sharedPreferences = getContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){
            String savedusername = sharedPreferences.getString("Username", "");
            String savedpassword = sharedPreferences.getString("Password", "");

            SignupTabFragment.credentials = new Credentials(savedusername, savedpassword);

            if(sharedPreferences.getBoolean("RememberMeCheckbox", false)){
                usernameEditText.setText(savedusername);
                passwordEditText.setText(savedpassword);
                Rememberme.setChecked(true);
            }

        }


/*      usernameEditText.setTranslationX(800);
        passwordEditText.setTranslationX(800);
        loginButton.setTranslationX(800);

        usernameEditText.setAlpha(v);
        passwordEditText.setAlpha(v);
        loginButton.setAlpha(v);

        usernameEditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordEditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        loginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();*/

        Rememberme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                sharedPreferencesEditor.putBoolean("RememberMeCheckbox", Rememberme.isChecked());
                sharedPreferencesEditor.apply();
            }


        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //gets whatever is input in the username field and converts the text into a string
                String inputUsername = usernameEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

                if (inputUsername.isEmpty() || inputUsername.isEmpty()) {
                    //Display a message toast to user to enter the details
                    Toast.makeText(getContext(), "Please enter name and password!", Toast.LENGTH_LONG).show();
                }else {

                    isValid = validate(inputUsername, inputPassword);
                    if (!isValid) {
                        Toast.makeText(getContext(), "Incorrect Username or Password", Toast.LENGTH_LONG).show();

                    } else {

                        Toast.makeText(getContext(), "Welcome " + inputUsername, Toast.LENGTH_LONG).show();

                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);

                    }
                }
            }
        });


        return view;

    }

    private boolean validate(String username, String userPassword){

        if(SignupTabFragment.credentials != null){
            if(username.equals(SignupTabFragment.credentials.getUsername()) && userPassword.equals(SignupTabFragment.credentials.getPassword())){
                return true;
            }
        }

        return false;
    }
}






/*

                if (inputUsername.isEmpty() || inputUsername.isEmpty()) {
                    //Display a message toast to user to enter the details
                    Toast.makeText(LoginActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                } else {
                    // uses validate function
                    isvalid = validate(inputUsername, inputPassword);
                    //Incorrect Credentials
                    if (!isvalid) {
                        Toast.makeText(LoginActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                    }

                    if (isvalid) {
                        //User inserts correct credentials
                        String welcome = getString(R.string.welcome) + usernameEditText;
                        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
                        // Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                        //add code to go to next activity
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                }
            }
        });


    return view;
    }

    //Variables
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    private final String Username = "Admin";
    private final String Password = "123456";

    boolean isvalid = false;

    @Nullable
    @Override
    public View onCreateView()
}
*/





