package com.example.mankey.helpsuprimentos.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.viewer.FormCadastro;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FormLogin extends AppCompatActivity {
    private TextView text_tela_cadastro;
    private EditText edtEmail, edtSenha;
    private FirebaseAuth meuAuth;
    private Button entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_login_activity);
        edtEmail = findViewById(R.id.edit_email);
        edtSenha = findViewById(R.id.edit_senha);
        entrar = findViewById(R.id.btnLogin);
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);

        meuAuth = FirebaseAuth.getInstance();

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                try {
                    meuAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(FormLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = meuAuth.getCurrentUser();
                                startActivity(new Intent(FormLogin.this, MainActivity.class));
                            } else {
                                Toast.makeText(FormLogin.this, "Credenciais incorretas", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }catch (OutOfMemoryError err){
                    Toast.makeText(FormLogin.this, "Falha, tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormLogin.this, FormCadastro.class));
            }
        });

    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = meuAuth.getCurrentUser();
//
//
//        if(currentUser != null){
//            Intent nav = new Intent(FormLogin.this, CadastrosActivity.class);
//            startActivity(nav);
//        }
//
//
//    }
}
