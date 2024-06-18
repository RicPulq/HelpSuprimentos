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

import com.example.mankey.helpsuprimentos.DataClass.IEntrega;
import com.example.mankey.helpsuprimentos.R;
import com.example.mankey.helpsuprimentos.controller.AdapterEntregaRow;
import com.example.mankey.helpsuprimentos.databinding.ActivityGetEntregaBinding;
import com.example.mankey.helpsuprimentos.model.Entrega;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class GetEntrega extends AppCompatActivity {
    DatabaseReference databaseReference;
    ActivityGetEntregaBinding binding;
    private Entrega entrega;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_entrega);

        entrega = new Entrega();
        binding = ActivityGetEntregaBinding.inflate(getLayoutInflater());

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

//        loadFeed();
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

//    private void loadFeed() {
//
//        ArrayList<IEntrega> entregaList = new ArrayList<IEntrega>();
//        this.entrega.getEntregas(entregaList, new Runnable() {
//            @Override
//            public void run() {
//                Collections.reverse(entregaList);
//                AdapterEntregaRow adapterEntrega = new AdapterEntregaRow(GetEntrega.this, entregaList);
//                binding.entregaRV.setAdapter(adapterEntrega);
//                adapterEntrega.notifyDataSetChanged();
//            }
//        });
//    }

}