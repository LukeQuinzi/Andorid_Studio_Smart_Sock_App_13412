package com.example.smartsock.ui.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smartsock.R;


public class SignupTabFragment extends Fragment {
    EditText email;
    EditText username;
    EditText phone;
    EditText password;
    EditText confirm_password;
    EditText gender;
    EditText DOB;
    Button signup;

    float v=0;

    String signinusername, signinpassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    public void onAttach(Context context) {
        sharedPreferences = context.getSharedPreferences("userFile",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup_tab, container, false);


        email = view.findViewById(R.id.signup_email);
        username = view.findViewById(R.id.signup_username);
        phone = view.findViewById(R.id.signup_phone);
        password = view.findViewById(R.id.signup_password);
        confirm_password = view.findViewById(R.id.signup_confirm_password);
        gender = view.findViewById(R.id.gender);
        DOB = view.findViewById(R.id.DOB);
        signup = view.findViewById(R.id.signup);

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

        });


        return view;
    }
}