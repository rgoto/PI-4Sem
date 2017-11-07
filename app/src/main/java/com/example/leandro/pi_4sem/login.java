package com.example.leandro.pi_4sem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    private EditText emailView;
    private EditText passView;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    finish();
                    startActivity(new Intent(getApplicationContext(), logado.class));
                }
            }
        };

        emailView = (EditText) findViewById(R.id.editEmail);
        passView = (EditText) findViewById(R.id.editPass);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        emailView.setText("adm@adm.com");
        passView.setText("123456");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailView.getText().toString().trim();
                String pass = passView.getText().toString().trim();

                if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(login.this, "E-mail Incorreto, Revise Por favor", Toast.LENGTH_LONG).show();
                    emailView.setError("Campo Inválido");
                    emailView.hasFocus();

                } else if (pass.isEmpty()) {
                    Toast.makeText(login.this, "Senha Inválib da, Revise Por favor", Toast.LENGTH_LONG).show();
                    emailView.setError("Campo Inválido!");
                    passView.hasFocus();

                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(login.this, "Campos Inválidos, Revise-os Por favor", Toast.LENGTH_LONG).show();
                    emailView.setError("Campo Inválido!");
                    passView.setError("Campo Inválido!");
                    emailView.hasFocus();

                } else  {
                    finish();
                    startActivity(new Intent(getApplicationContext(), logado.class));
                }
            }

        });
    }
}