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
import com.example.mankey.helpsuprimentos.model.Armazem;
import com.example.mankey.helpsuprimentos.model.Role;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastroArmazem extends AppCompatActivity {
    private EditText edtNomeArmazem;
    private EditText edtLocalizacao;
    private EditText edtResponsavelUUID;
    private Button btnSalvarArmazem;
    private DatabaseReference databaseReference; // Referência para o banco de dados do Firebase
    private int armazemIndex; // Variável para armazenar o índice atual das categorias

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.armazem);

        edtNomeArmazem = findViewById(R.id.edtNomeArmazem);
        edtLocalizacao = findViewById(R.id.edtLocalizacao);
        edtResponsavelUUID = findViewById(R.id.edtResponsavelUUID);

        databaseReference = FirebaseDatabase.getInstance().getReference("armazem");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Atualiza a variável roleIndex com o número de role existentes
                armazemIndex = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Tratar possíveis erros ao acessar o Firebase
            }
        });
        btnSalvarArmazem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarArmazem(); // Chama o método para salvar a categoria
            }
        });
    }
    private void salvarArmazem(){
        // Obtém o nome da categoria do campo de entrada de texto
        String nomeArmazem = edtNomeArmazem.getText().toString().trim();
        String responsavelUUID = edtResponsavelUUID.getText().toString().trim();
        String localizacao = edtLocalizacao.getText().toString().trim();

        // Verificar se o nome da categoria não está vazio
        if (!nomeArmazem.isEmpty()){
            // Incrementa o índice da categoria para criar uma nova categoria
            final int index = armazemIndex + 1;

            // Cria um objeto Categoria com o índice e nome fornecidos
            Armazem role = new Armazem("Armazem" + index, responsavelUUID, nomeArmazem, localizacao);

            // Salva a categoria no Firebase usando o índice como chave
            databaseReference.child("Role" + index).setValue(role);

            // Exibe uma mensagem de sucesso
            Toast.makeText(this, "Role salva!", Toast.LENGTH_SHORT).show();
            finish(); // Encerra a atividade
        } else {
            // Exibe uma mensagem de erro caso o nome da categoria esteja vazio
            Toast.makeText(this, "Digite o nome da Role!", Toast.LENGTH_SHORT).show();
        }
    }
}
