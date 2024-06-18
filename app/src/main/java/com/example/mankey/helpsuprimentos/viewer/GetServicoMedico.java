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

public class GetServicoMedico extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_servico_medico);

        EditText etServicoMedico = findViewById(R.id.etServicoMedico);
        Button result = findViewById(R.id.consultaServico);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String servico = etServicoMedico.getText().toString();

                if(!servico.isEmpty()){
                    leituraBD(servico);
                } else {
                    Toast.makeText(GetServicoMedico.this, "Digite Serviço válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String entrega) {
        databaseReference = FirebaseDatabase.getInstance().getReference("servico");
        databaseReference.child(entrega).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetServicoMedico.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String tipoAtendimento = String.valueOf(dataSnapshot.child("tipoAtendimento").getValue());
                        String usuarioUUID = String.valueOf(dataSnapshot.child("usuarioUUID").getValue());
                        String latitude = String.valueOf(dataSnapshot.child("latitude").getValue());
                        String longitude = String.valueOf(dataSnapshot.child("longitude").getValue());

                        TextView tvServico = findViewById(R.id.tvServico);
                        TextView tvServicoUsuario = findViewById(R.id.tvServicoUsuario);
                        TextView tvServicoLatitude = findViewById(R.id.tvServicoLatitude);
                        TextView tvServicoLongitude = findViewById(R.id.tvServicoLongitude);


                        tvServico.setText(tipoAtendimento);
                        tvServicoUsuario.setText(usuarioUUID);
                        tvServicoLatitude.setText(latitude);
                        tvServicoLongitude.setText(longitude);
                        Toast.makeText(GetServicoMedico.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetServicoMedico.this, "Serviço não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetServicoMedico.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}