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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CadastroRelatorio extends AppCompatActivity {

    private EditText usuarioUUID;
    private EditText tipoRelatorio;
    private EditText conteudoRelatorio;
    private Button btnSalvar;
    private Calendar calendar = Calendar.getInstance();
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int relatorioIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relatorio_cadastro);

        usuarioUUID = findViewById(R.id.edtUsuarioUUID);
        tipoRelatorio = findViewById(R.id.edtTipo);
        conteudoRelatorio = findViewById(R.id.edtConteudo);
        btnSalvar = findViewById(R.id.btnSalvarPonto);

        databaseReference = FirebaseDatabase.getInstance().getReference("relatorio");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                relatorioIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarRelatorio();
            }
        });
    }
    private void salvarRelatorio(){
        String usuario = usuarioUUID.getText().toString().trim();
        String tipo = tipoRelatorio.getText().toString().trim();
        String conteudo = conteudoRelatorio.getText().toString().trim();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String dataAtual = dia+"/"+mes+"/"+ano;

        if (!usuario.isEmpty()){
            final int index = relatorioIndex + 1;

            Relatorio relatorio = new Relatorio("Relatorio"+index, tipo, conteudo, dataAtual
            );

            databaseReference.child("Relatorio"+index).setValue(relatorio);

            Toast.makeText(this, "Relatório cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }
}
