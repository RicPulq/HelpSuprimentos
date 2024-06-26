package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.model.Role;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CadastrarRole extends AppCompatActivity {
    private EditText edtNomeRole;
    private Button btnSalvarRole;
    private DatabaseReference databaseReference; // Referência para o banco de dados do Firebase
    private int roleIndex; // Variável para armazenar o índice atual das categorias
    private int accessLevel; // Variável para armazenar o nível de acesso

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.role_cadastro); // Define o layout da atividade

        // Inicialização dos componentes da interface
        edtNomeRole = findViewById(R.id.edtNomeRole);
        btnSalvarRole = findViewById(R.id.btnSalvarRole);

        // Inicialização a referência do Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("role");

        // Obtém o índice atual de role do Firebase
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Atualiza a variável roleIndex com o número de role existentes
                roleIndex = (int) snapshot.getChildrenCount();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Tratar possíveis erros ao acessar o Firebase
            }
        });

        // Define a ação do botão salvar
        btnSalvarRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarRole(); // Chama o método para salvar a categoria
            }
        });
    }

    // Método para salvar a categoria no Firebase
    private void salvarRole(){
        // Obtém o nome da categoria do campo de entrada de texto
        String nomeRole = edtNomeRole.getText().toString().trim();

        // Verificar se o nome da categoria não está vazio
        if (!nomeRole.isEmpty()){
            // Incrementa o índice da categoria para criar uma nova categoria
            final int index = roleIndex + 1;
            final int nivelAccess = accessLevel + 1;

            // Cria um objeto Categoria com o índice e nome fornecidos
            Role role = new Role("Role" + index, nomeRole, nivelAccess);

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
