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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.smartsock.MainActivity;
import com.example.smartsock.R;

public class LoginTabFragment extends Fragment {

    EditText usernameEditText;
    EditText passwordEditText;
    Button loginButton;

    private final String Username = "Admin";
    private final String Password = "123456";

    boolean isvalid = false;

    String inputUsername, inputPassword;

    float v = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(Context context) {
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_tab, container, false);

        usernameEditText = view.findViewById(R.id.username);
        passwordEditText = view.findViewById(R.id.password);
        loginButton = view.findViewById(R.id.login);

        usernameEditText.setTranslationX(800);
        passwordEditText.setTranslationX(800);
        loginButton.setTranslationX(800);

        usernameEditText.setAlpha(v);
        passwordEditText.setAlpha(v);
        loginButton.setAlpha(v);

        usernameEditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        passwordEditText.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        loginButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gets whatever is input in the username field and converts the text into a string
                inputUsername = usernameEditText.getText().toString();
                inputPassword = passwordEditText.getText().toString();

                if (inputUsername.isEmpty() || inputUsername.isEmpty()) {
                    //Display a message toast to user to enter the details
                    Toast.makeText(getContext(), "Please enter name and password!", Toast.LENGTH_LONG).show();
                }else{
                    if (inputUsername.equals(Username) && inputPassword.equals(Password)) {
                        Toast.makeText(getContext(), "Login", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MainActivity.class);
                        getActivity().startActivity(intent);

                    }

                }
            }
        });


        return view;

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





