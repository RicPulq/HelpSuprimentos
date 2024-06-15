package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mankey.helpsuprimentos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetArmazem extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DatabaseReference reference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_armazem);

        EditText etArmazem = findViewById(R.id.etArmazem);
        Button result = findViewById(R.id.consultaArmazem);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String armazem = etArmazem.getText().toString();

                if(!armazem.isEmpty()){
                    leituraBD(armazem);
                } else {
                    Toast.makeText(GetArmazem.this, "Digite Armazem válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String armazem) {
        databaseReference = FirebaseDatabase.getInstance().getReference("armazem");
        databaseReference.child(armazem).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetArmazem.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String nome = String.valueOf(dataSnapshot.child("nome").getValue());
                        String latitude = String.valueOf(dataSnapshot.child("latitude").getValue());
                        String longitude = String.valueOf(dataSnapshot.child("longitude").getValue());

                        TextView tvNome = findViewById(R.id.tvNome);
                        TextView tvLatitude = findViewById(R.id.tvLatitude);
                        TextView tvLongitude = findViewById(R.id.tvLongitude);


                        tvNome.setText(nome);
                        tvLatitude.setText(latitude);
                        tvLongitude.setText(longitude);
                        Toast.makeText(GetArmazem.this, "Sucesso", Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(GetArmazem.this, "Armazem não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetArmazem.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}