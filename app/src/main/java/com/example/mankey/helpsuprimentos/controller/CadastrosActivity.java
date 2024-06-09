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
import com.example.mankey.helpsuprimentos.viewer.CadastroEntrega;
import com.example.mankey.helpsuprimentos.viewer.CadastroPontoDistribuicao;
import com.example.mankey.helpsuprimentos.viewer.CadastroRelatorio;
import com.example.mankey.helpsuprimentos.viewer.CadastroServicoMedico;
import com.example.mankey.helpsuprimentos.viewer.CadastroSuprimento;

public class CadastrosActivity extends AppCompatActivity {
    private Button CadastrarRole, CadastrarPontoDistribuicao, CadastrarArmazem;
    private Button CadastrarRelatorio, CadastrarServicoMedico, CadastrarSuprimento, CadastrarEntrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastros_activity);

        CadastrarRole = findViewById(R.id.btnCadastrarRole);
        CadastrarRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastrarRole.class));
            }
        });

        CadastrarPontoDistribuicao = findViewById(R.id.btnCadastrarPontoDistribuicao);
        CadastrarPontoDistribuicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroPontoDistribuicao.class));
            }
        });

        CadastrarArmazem = findViewById(R.id.btnCadastrarArmazem);
        CadastrarArmazem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroArmazem.class));
            }
        });

        CadastrarRelatorio = findViewById(R.id.btnCadastrarRelatorio);
        CadastrarRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroRelatorio.class));
            }
        });

        CadastrarServicoMedico = findViewById(R.id.btnCadastrarServicoMedico);
        CadastrarServicoMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroServicoMedico.class));
            }
        });

        CadastrarSuprimento = findViewById(R.id.btnCadastrarSuprimento);
        CadastrarSuprimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroSuprimento.class));
            }
        });

        CadastrarEntrega = findViewById(R.id.btnCadastrarEntrega);
        CadastrarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastrosActivity.this, CadastroEntrega.class));
            }
        });
    }
}