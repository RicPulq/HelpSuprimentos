package com.example.mankey.helpsuprimentos.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.viewer.CadastrarRole;
import com.example.mankey.helpsuprimentos.viewer.CadastroArmazem;

public class CadastrosActivity extends AppCompatActivity {
    private Button CadastrarRole;
    private Button CadastrarPontoDistribuicao;
    private Button CadastrarArmazem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastros_activity);

        CadastrarRole = findViewById(R.id.btnCadastrarRole);
        CadastrarRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, com.example.mankey.helpsuprimentos.viewer.CadastrarRole.class));
            }
        });

        CadastrarPontoDistribuicao = findViewById(R.id.btnCadastrarPontoDistribuicao);
        CadastrarPontoDistribuicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, com.example.mankey.helpsuprimentos.viewer.CadastroPontoDistribuicao.class));
            }
        });

        CadastrarArmazem = findViewById(R.id.btnCadastrarArmazem);
        CadastrarArmazem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, com.example.mankey.helpsuprimentos.viewer.CadastroArmazem.class));
            }
        });
    }
}