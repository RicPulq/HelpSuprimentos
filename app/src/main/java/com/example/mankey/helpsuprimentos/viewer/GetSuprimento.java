package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetSuprimento extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_suprimento);
        EditText etSuprimentoNome = findViewById(R.id.etSuprimentoNome);
        Button result = findViewById(R.id.consultaSuprimento);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String suprimento = etSuprimentoNome.getText().toString().trim();

                if(!suprimento.isEmpty()){
                    leituraBD(suprimento);
                } else {
                    Toast.makeText(GetSuprimento.this, "Digite Suprimento válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String suprimento) {
        databaseReference = FirebaseDatabase.getInstance().getReference("suprimento");
        databaseReference.child(suprimento).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetSuprimento.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String nome = String.valueOf(dataSnapshot.child("nome").getValue());
                        String quantidade = String.valueOf(dataSnapshot.child("quantidade").getValue());
                        String armazemUUID = String.valueOf(dataSnapshot.child("armazemUUID").getValue());
                        String data = String.valueOf(dataSnapshot.child("data").getValue());

                        TextView tvSuprimento_nome = findViewById(R.id.tvSuprimento_nome);
                        TextView tvQuantidade = findViewById(R.id.tvQuantidade);
                        TextView tvArmazemUUID = findViewById(R.id.tvArmazemUUID);
                        TextView tvDataRegistro = findViewById(R.id.tvDataRegistro);


                        tvSuprimento_nome.setText(nome);
                        tvQuantidade.setText(quantidade);
                        tvArmazemUUID.setText(armazemUUID);
                        tvDataRegistro.setText(data);
                        Toast.makeText(GetSuprimento.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetSuprimento.this, "Suprimento não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetSuprimento.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}