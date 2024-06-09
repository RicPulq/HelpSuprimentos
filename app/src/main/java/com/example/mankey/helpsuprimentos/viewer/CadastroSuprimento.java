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
import com.example.mankey.helpsuprimentos.model.Suprimento;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class CadastroSuprimento extends AppCompatActivity {
    private EditText nomeSuprimento;
    private EditText quantidade;
    private EditText armazemUUID;
    private Button btnSalvar;
    private Calendar calendar = Calendar.getInstance();
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int suprimentoIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suprimento_cadastro);

        nomeSuprimento = findViewById(R.id.edtNome);
        quantidade = findViewById(R.id.edtQuantidade);
        armazemUUID = findViewById(R.id.edtArmazemUUID);
        btnSalvar = findViewById(R.id.btnSalvarSuprimento);

        databaseReference = FirebaseDatabase.getInstance().getReference("suprimento");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                suprimentoIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarSuprimento();
            }
        });
    }
    private void salvarSuprimento(){
        String nome_suprimento = nomeSuprimento.getText().toString().trim();
        String qtdd = quantidade.getText().toString().trim();
        String armazem = armazemUUID.getText().toString().trim();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        String dataAtual = dia+"/"+mes+"/"+ano;

        if (!nome_suprimento.isEmpty() || !armazem.isEmpty()){
            final int index = suprimentoIndex + 1;

            Suprimento suprimento = new Suprimento("Suprimento"+index, nome_suprimento, qtdd, armazem, dataAtual);

            databaseReference.child("Suprimento"+index).setValue(suprimento);

            Toast.makeText(this, "Suprimento cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show();
        }
    }
}
