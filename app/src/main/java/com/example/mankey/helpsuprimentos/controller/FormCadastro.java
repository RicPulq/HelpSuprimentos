package com.example.mankey.helpsuprimentos.controller;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.google.firebase.database.DatabaseReference;

public class FormCadastro extends AppCompatActivity {
    private EditText edit_email, edit_senha;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_cadastro_activity);

        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        button = findViewById(R.id.btnCadastrarUser);

    }
}
