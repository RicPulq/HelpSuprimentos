package com.example.mankey.helpsuprimentos.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;

public class FormLogin extends AppCompatActivity {
    private TextView text_tela_cadastro;
    private Button entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_login_activity);

        entrar = findViewById(R.id.btnLogin);
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormLogin.this, com.example.mankey.helpsuprimentos.controller.CadastrosActivity.class));
            }
        });

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormLogin.this, com.example.mankey.helpsuprimentos.controller.FormCadastro.class));
            }
        });

    }
}
