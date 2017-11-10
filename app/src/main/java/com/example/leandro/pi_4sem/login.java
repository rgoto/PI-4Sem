package com.example.leandro.pi_4sem;

import android.content.Intent;
import android.os.Handler;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {

    private EditText emailView;
    private EditText passView;
    private Button loginBtn;
    private int success, id;
    private String nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailView = (EditText) findViewById(R.id.editEmail);
        passView = (EditText) findViewById(R.id.editPass);
        loginBtn = (Button) findViewById(R.id.btnLogin);

        emailView.setText("adm@adm.com");
        passView.setText("123456");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = emailView.getText().toString().trim();
                final String pass = passView.getText().toString().trim();

                login(email, pass);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
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

                        } else if (success == 1) {
                            Intent it = new Intent(getApplicationContext(), logado.class);
                            it.putExtra("id", id);
                            it.putExtra("nome", nome);
                            finish();
                            startActivity(it);
                        }
                    }
                }, 1000);
            }
        });
    }

    private void login (String email, String senha) {
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="http://pi4sem.rbbr.com.br/teste/login.php?email=" + email + "&senha=" + senha;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                success = 1;
                                JSONObject object = response.getJSONArray("dados").getJSONObject(0);
                                id = object.getInt("id");
                                nome = object.getString("nome");

                            } else {
                                Toast.makeText(getApplicationContext(),
                                        response.getString("message").trim(),Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);
    }
}