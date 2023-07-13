package com.example.appinterface.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.appinterface.R;

public class SignUpActivity extends AppCompatActivity {

    EditText edtUsername, edtName, edtPassword, edtContact;
    AppCompatButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtName = findViewById(R.id.edt_name);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtContact = findViewById(R.id.edt_contact);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if(register_validation()){
//                    if(registe_auth()){

                        Intent iLogin = new Intent(SignUpActivity.this, SignInActivity.class);
                        startActivity(iLogin);
//                    }
//                }
            }
        });

    }
}