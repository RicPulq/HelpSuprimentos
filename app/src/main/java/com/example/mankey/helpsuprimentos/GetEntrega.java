package com.example.mankey.helpsuprimentos;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mankey.helpsuprimentos.viewer.GetArmazem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetEntrega extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_entrega);
        EditText etEntrega = findViewById(R.id.etEntrega);
        Button result = findViewById(R.id.consultaEntrega);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entrega = etEntrega.getText().toString();

                if(!entrega.isEmpty()){
                    leituraBD(entrega);
                } else {
                    Toast.makeText(GetEntrega.this, "Digite Entrega válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String entrega) {
        databaseReference = FirebaseDatabase.getInstance().getReference("entrega");
        databaseReference.child(entrega).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetEntrega.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String suprimento = String.valueOf(dataSnapshot.child("suprimentoUUID").getValue());
                        String pontoDistrib = String.valueOf(dataSnapshot.child("pontoDistribuicaoUUID").getValue());
                        String status = String.valueOf(dataSnapshot.child("status").getValue());
                        String data = String.valueOf(dataSnapshot.child("data").getValue());

                        TextView tvSuprimento = findViewById(R.id.tvSuprimento);
                        TextView tvDistribuicao = findViewById(R.id.tvDistribuicao);
                        TextView tvStatus = findViewById(R.id.tvStatus);
                        TextView tvData = findViewById(R.id.tvData);


                        tvSuprimento.setText(suprimento);
                        tvDistribuicao.setText(pontoDistrib);
                        tvStatus.setText(status);
                        tvData.setText(data);
                        Toast.makeText(GetEntrega.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetEntrega.this, "Armazem não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetEntrega.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}