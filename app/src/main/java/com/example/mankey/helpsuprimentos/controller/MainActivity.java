package com.example.mankey.helpsuprimentos.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.viewer.GetServicoMedico;
import com.example.mankey.helpsuprimentos.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button Cadastrar;
    private Button Visualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cadastrar = findViewById(R.id.btnCadastrar);
        Cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, com.example.mankey.helpsuprimentos.controller.CadastrosActivity.class));
            }
        });

        Visualizar = findViewById(R.id.btnVisualizar);
        Visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, VisualizarActivity.class));
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser == null){
            Intent intent = new Intent(MainActivity.this, FormLogin.class);
            startActivity(intent);
            finish();
        }
    }
}