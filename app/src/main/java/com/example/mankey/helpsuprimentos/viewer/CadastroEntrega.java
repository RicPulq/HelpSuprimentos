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
import com.example.mankey.helpsuprimentos.model.Entrega;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CadastroEntrega extends AppCompatActivity {
    private EditText statusEntrega, suprimentoUUID, distribuicaoUUID;
    private Button btnSalvar;

    private Calendar calendar = Calendar.getInstance();
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int entregaIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrega_cadastro);

        statusEntrega = findViewById(R.id.edtStatus);
        suprimentoUUID = findViewById(R.id.edtASuprimentoUUID);
        distribuicaoUUID = findViewById(R.id.edtAPontoDistribuicaoUUID);
        btnSalvar = findViewById(R.id.btnSalvarEntrega);

        databaseReference = FirebaseDatabase.getInstance().getReference("entrega");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                entregaIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEntrega();
            }
        });
    }
    private void salvarEntrega(){
        String status = statusEntrega.getText().toString().trim();
        String suprimento = suprimentoUUID.getText().toString().trim();
        String distribuicao = distribuicaoUUID.getText().toString().trim();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String dataAtual = dia+"/"+mes+"/"+ano;

        if (!status.isEmpty()||!suprimento.isEmpty()||!distribuicao.isEmpty()){
            final int index = entregaIndex + 1;

            Entrega entrega = new Entrega("Entrega"+index, distribuicao, suprimento, dataAtual, status);

            databaseReference.child("Entrega"+index).setValue(entrega);

            Toast.makeText(this, "Entrega cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }
}
