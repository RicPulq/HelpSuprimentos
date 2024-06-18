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

public class GetPontoDistribuicao extends AppCompatActivity {
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_ponto_distribuicao);
        EditText etPontoDistrib = findViewById(R.id.etPontoDistrib);
        Button result = findViewById(R.id.consultaDistribuicao);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pontoDist = etPontoDistrib.getText().toString();

                if(!pontoDist.isEmpty()){
                    leituraBD(pontoDist);
                } else {
                    Toast.makeText(GetPontoDistribuicao.this, "Digite Entrega válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String pontoDistribuicao) {
        databaseReference = FirebaseDatabase.getInstance().getReference("pontodistribuicao");
        databaseReference.child(pontoDistribuicao).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetPontoDistribuicao.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String nome = String.valueOf(dataSnapshot.child("nome").getValue());
                        String latitude = String.valueOf(dataSnapshot.child("pontoDistribuicaoUUID").getValue());
                        String longitude = String.valueOf(dataSnapshot.child("status").getValue());

                        TextView tvSuprimento = findViewById(R.id.tvSuprimento);
                        TextView tvDistribuicao = findViewById(R.id.tvDistribuicao);
                        TextView tvStatus = findViewById(R.id.tvStatus);
                        TextView tvData = findViewById(R.id.tvData);


//                        tvSuprimento.setText(suprimento);
//                        tvDistribuicao.setText(pontoDistrib);
//                        tvStatus.setText(status);
//                        tvData.setText(data);
                        Toast.makeText(GetPontoDistribuicao.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetPontoDistribuicao.this, "Armazem não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetPontoDistribuicao.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}