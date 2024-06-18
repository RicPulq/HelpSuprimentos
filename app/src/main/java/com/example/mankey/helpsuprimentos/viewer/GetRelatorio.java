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

public class GetRelatorio extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_relatorio);
        EditText etRelatorio = findViewById(R.id.etRelatorio);
        Button result = findViewById(R.id.consultaRelatorio);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String relatorio = etRelatorio.getText().toString();

                if(!relatorio.isEmpty()){
                    leituraBD(relatorio);
                } else {
                    Toast.makeText(GetRelatorio.this, "Digite Entrega válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String entrega) {
        databaseReference = FirebaseDatabase.getInstance().getReference("relatorio");
        databaseReference.child(entrega).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetRelatorio.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String usuario = String.valueOf(dataSnapshot.child("usuarioUUID").getValue());
                        String tipo = String.valueOf(dataSnapshot.child("tipo").getValue());
                        String conteudo = String.valueOf(dataSnapshot.child("conteudo").getValue());
                        String data = String.valueOf(dataSnapshot.child("dataCriaocao").getValue());

                        TextView tvUsuario = findViewById(R.id.tvUsuario);
                        TextView tvTipo = findViewById(R.id.tvTipo);
                        TextView tvConteudo = findViewById(R.id.tvConteudo);
                        TextView tvDataCriacao = findViewById(R.id.tvDataCriacao);


                        tvUsuario.setText(usuario);
                        tvTipo.setText(tipo);
                        tvConteudo.setText(conteudo);
                        tvDataCriacao.setText(data);
                        Toast.makeText(GetRelatorio.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetRelatorio.this, "Relatorio não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetRelatorio.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}