package com.example.smartsock.ui.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartsock.MainActivity;
import com.example.smartsock.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class LoginActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        TabItem tabLogin = findViewById(R.id.tab_Login);
        TabItem tabSignup = findViewById(R.id.tab_Signup);
        final ViewPager viewPager = findViewById(R.id.view_pager);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
}

/*
    }
    //Variables
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button signupButton;

    private final String Username = "Admin";
    private final String Password = "123456";

    boolean isvalid = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login_tab);


        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        signupButton = findViewById(R.id.button_signup);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gets whatever is input in the username field and converts the text into a string
                String inputUsername = usernameEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

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

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, MainActivity.class));

            }
        });

    }

    //validate function if credentials are true or false
    private boolean validate(String username, String password) {

        if (username.equals(Username) && password.equals(Password)) {
            return true;
        }

        return false;
    }
}*/





