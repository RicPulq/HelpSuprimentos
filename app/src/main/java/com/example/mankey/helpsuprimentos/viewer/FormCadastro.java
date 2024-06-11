package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormCadastro extends AppCompatActivity {
    private EditText edit_email, edit_senha, edit_nome, edit_cpf;
    private Button button;
    private FirebaseAuth meuAuth;
    private DatabaseReference databaseReference;
    private int usuarioIndex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_cadastro_activity);

        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        edit_nome = findViewById(R.id.edit_nome);
        edit_cpf = findViewById(R.id.edit_cpf);
        button = findViewById(R.id.btnCadastrarUser);

        meuAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("usuario");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usuarioIndex = (int) snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(FormCadastro.this, "Algo deu errado!", Toast.LENGTH_LONG).show();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criaUser();
            }
        });

    }

    private void criaUser() {
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();
        String nome = edit_nome.getText().toString();
        String cpf = edit_cpf.getText().toString();

        if (TextUtils.isEmpty(email)){
            edit_email.setError("Email não pode ser vazio!");
            edit_email.requestFocus();
        }else if(TextUtils.isEmpty(senha)){
            edit_senha.setError("Senha não pode ser vazia!");
            edit_senha.requestFocus();
        }else if(senha.length() < 6){
            edit_senha.setError("Senha não pode ser menos que 6 digitos!");
            edit_senha.requestFocus();
        }else{
            meuAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(FormCadastro.this,"CADASTRADO COM SUCESSO!", Toast.LENGTH_SHORT).show();
                        final int index = usuarioIndex + 1;

                        Usuario userData = new Usuario("usuario"+index, nome, email, "aa2131f13f",senha,cpf);

                        databaseReference.child("usuario"+index).setValue(userData);
                        finish();
                    }else{
                        Toast.makeText(FormCadastro.this,"ERRO AO CADASTRAR!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
