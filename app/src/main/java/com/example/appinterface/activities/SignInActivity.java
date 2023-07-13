package com.example.appinterface.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appinterface.R;

public class SignInActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    AppCompatButton btnSignin, btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnSignin = findViewById(R.id.btn_signin);
        btnSignup = findViewById(R.id.btn_signup);

        btnSignin.setOnClickListener(new onClickList());
        btnSignup.setOnClickListener(new onClickList());

    }

    private class onClickList implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            int i = view.getId();

            if(i == R.id.btn_signin){

//                if(signin_validation()){
//                    if(signin_auth()){
                        Intent iHome = new Intent(SignInActivity.this, HomeActivity.class);
                        startActivity(iHome);
                        finishAffinity();
//                    }
//                }
            } else if(i == R.id.btn_signup){

                Intent iRegister = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(iRegister);
                finish();
            }
        }
    }
}