package com.example.mankey.helpsuprimentos.viewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mankey.helpsuprimentos.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetRole extends AppCompatActivity {
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        DatabaseReference reference;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_role);

        EditText etRole = findViewById(R.id.etRole);
        Button result = findViewById(R.id.readdataBtn);

        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String role = etRole.getText().toString();

                if(!role.isEmpty()){
                    leituraBD(role);
                } else {
                    Toast.makeText(GetRole.this, "Digite Role válido!", Toast.LENGTH_SHORT).show();;
                }
            }

        });
    }

    private void leituraBD(String username) {
        databaseReference = FirebaseDatabase.getInstance().getReference("role");
        databaseReference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()){
                    if(task.getResult().exists()){
                        Toast.makeText(GetRole.this, "Consulta realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String nome = String.valueOf(dataSnapshot.child("nome").getValue());
                        String nivelAcesso = String.valueOf(dataSnapshot.child("nivelAcesso").getValue());

                        TextView tvNome = findViewById(R.id.tvNome);
                        TextView tvNivelAcesso = findViewById(R.id.tvNivelAcesso);

                        tvNome.setText(nome);
                        tvNivelAcesso.setText(nivelAcesso);
                    } else {
                        Toast.makeText(GetRole.this, "Role não existe", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GetRole.this, "ERROR!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
