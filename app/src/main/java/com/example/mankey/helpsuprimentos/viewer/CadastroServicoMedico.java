package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.model.Relatorio;
import com.example.mankey.helpsuprimentos.model.ServicoMedico;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CadastroServicoMedico extends AppCompatActivity {
    private EditText usuarioUUID;
    private EditText tipo;
    private EditText edtLocalizacao;
    private Button btnSalvarServico;
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int servicoIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.servico_medico_cadastro);

        usuarioUUID = findViewById(R.id.edtUsuarioUUID);
        edtLocalizacao = findViewById(R.id.edtLocalizacao);
        tipo = findViewById(R.id.edtTipoAtendimento);
        btnSalvarServico = findViewById(R.id.btnSalvarServico);

        databaseReference = FirebaseDatabase.getInstance().getReference("servico");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                servicoIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSalvarServico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarServico();
            }
        });
    }
    private void salvarServico(){
        String usuario = usuarioUUID.getText().toString().trim();
        String tipoAtendimento = tipo.getText().toString().trim();
        String localizacao = edtLocalizacao.getText().toString().trim();


        if (!usuario.isEmpty()){
            final int index = servicoIndex + 1;

            ServicoMedico servico = new ServicoMedico("ServicoAtendimento"+index, usuario, localizacao, tipoAtendimento);

            databaseReference.child("Relatorio"+index).setValue(servico);

            Toast.makeText(this, "Serviço Médico cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }
}
