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
import com.example.mankey.helpsuprimentos.model.PontoDistribuicao;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastroPontoDistribuicao extends AppCompatActivity {
    private EditText edtNomePonto;
    private EditText edtCoordenadas;
    private Button btnSalvarPonto;
    private DatabaseReference databaseReference; // Referência para o banco de dados
    private int distribuicaoIndex; // Variável para armazenar o índice atual dos Pontos de Distribuição

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ponto_distribuicao);

        // Inicialização dos componentes da interface
        edtNomePonto = findViewById(R.id.edtNomePonto);
        edtCoordenadas = findViewById(R.id.edtCoordenadas);
        btnSalvarPonto = findViewById(R.id.btnSalvarPonto);

        // Inicializa a referência ao Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("pontodistribuicao");

        // Obtém o índice atual de Ponto de Distribuição do Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Atualiza a variável distribuicaoIndex com o número de Pontos existentes
                distribuicaoIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Tratar possíveis erros ao acessar o Firebase
            }
        });

        btnSalvarPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarPonto(); // Chama o método para salvar o Ponto de Distribuição
            }
        });
    }
    private void salvarPonto(){
        // Obtém o nome do Ponto de Distribuição do campo de entrada de texto
        String nomeDistribuicao = edtNomePonto.getText().toString().trim();
        // Obtém a coordenada do Ponto de Distribuição do campo de entrada de texto
        String coordenadasDistribuicao = edtCoordenadas.getText().toString().trim();

        if (!nomeDistribuicao.isEmpty()){
            // Incrementa o índice da Ponto para criar uma nova Ponto
            final int index = distribuicaoIndex + 1;

            // Cria um objeto PontoDistribuicao com o índice e nome fornecidos
            PontoDistribuicao pontoDistribuicao = new PontoDistribuicao("Ponto"+index, nomeDistribuicao, coordenadasDistribuicao);

            // Salva a categoria no Firebase usando o índice como chave
            databaseReference.child("Ponto"+index).setValue(coordenadasDistribuicao);

            // Exibe uma mensagem de sucesso
            Toast.makeText(this, "Ponto de Distribuição cadastrado!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Exibe mensagem de erro
            Toast.makeText(this, "Digite o nome do Ponto de Distribuição", Toast.LENGTH_SHORT).show();
        }
    }
}
