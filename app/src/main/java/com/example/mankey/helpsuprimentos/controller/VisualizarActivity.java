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
import com.example.mankey.helpsuprimentos.databinding.ActivityVisualizarBinding;
import com.example.mankey.helpsuprimentos.viewer.CadastrarRole;
import com.example.mankey.helpsuprimentos.viewer.CadastroArmazem;
import com.example.mankey.helpsuprimentos.viewer.CadastroEntrega;
import com.example.mankey.helpsuprimentos.viewer.CadastroPontoDistribuicao;
import com.example.mankey.helpsuprimentos.viewer.CadastroRelatorio;
import com.example.mankey.helpsuprimentos.viewer.CadastroServicoMedico;
import com.example.mankey.helpsuprimentos.viewer.CadastroSuprimento;
import com.example.mankey.helpsuprimentos.viewer.GetArmazem;
import com.example.mankey.helpsuprimentos.viewer.GetEntrega;
import com.example.mankey.helpsuprimentos.viewer.GetPontoDistribuicao;
import com.example.mankey.helpsuprimentos.viewer.GetRelatorio;
import com.example.mankey.helpsuprimentos.viewer.GetRole;
import com.example.mankey.helpsuprimentos.viewer.GetServicoMedico;
import com.example.mankey.helpsuprimentos.viewer.GetSuprimento;

public class VisualizarActivity extends AppCompatActivity {
    private Button VisualizarRole, VisualizarPontoDistribuicao, VisualizarArmazem;
    private Button VisualizarRelatorio, VisualizarServicoMedico, VisualizarSuprimento;
    private ActivityVisualizarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisualizarBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_visualizar);
        setContentView(binding.getRoot());
        VisualizarRole = findViewById(R.id.btnVisualizarRole);
        VisualizarRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetRole.class));
            }
        });

        VisualizarPontoDistribuicao = findViewById(R.id.btnVisualizarPontoDistribuicao);
        VisualizarPontoDistribuicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetPontoDistribuicao.class));
            }
        });

        VisualizarArmazem = findViewById(R.id.btnVisualizarArmazem);
        VisualizarArmazem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetArmazem.class));
            }
        });

        VisualizarRelatorio = findViewById(R.id.btnVisualizarRelatorio);
        VisualizarRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetRelatorio.class));
            }
        });

        VisualizarServicoMedico = findViewById(R.id.btnVisualizarServicoMedico);
        VisualizarServicoMedico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetServicoMedico.class));
            }
        });

        VisualizarSuprimento = findViewById(R.id.btnVisualizarSuprimento);
        VisualizarSuprimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetSuprimento.class));
            }
        });

//        VisualizarEntrega = findViewById(R.id.btnVisualizarEntrega);
        binding.btnVisualizarEntrega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VisualizarActivity.this, GetEntrega.class));
            }
        });
    }
}