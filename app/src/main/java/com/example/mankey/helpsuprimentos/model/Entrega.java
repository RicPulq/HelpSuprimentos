package com.example.mankey.helpsuprimentos.model;

import static com.example.mankey.helpsuprimentos.DataClass.IEntrega.suprimentoUUID;

import android.util.Log;

import com.example.mankey.helpsuprimentos.DataClass.IEntrega;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Entrega {
//    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("entrega");
    private String id;
    private String pontoDistribuicaoUUID;
    private String suprimentoUUID;
    private String data;
    private String status;

    public Entrega(){

    }

    public Entrega(String id, String pontoDistribuicaoUUID, String suprimentoUUID, String data, String status) {
        this.id = id;
        this.pontoDistribuicaoUUID = pontoDistribuicaoUUID;
        this.suprimentoUUID = suprimentoUUID;
        this.data = data;
        this.status = status;
    }

    public String getPontoDistribuicaoUUID() {
        return pontoDistribuicaoUUID;
    }

    public void setPontoDistribuicaoUUID(String pontoDistribuicaoUUID) {
        this.pontoDistribuicaoUUID = pontoDistribuicaoUUID;
    }

    public String getSuprimentoUUID() {
        return suprimentoUUID;
    }

    public void setSuprimentoUUID(String suprimentoUUID) {
        this.suprimentoUUID = suprimentoUUID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public void getEntregas(ArrayList<IEntrega> list, final Runnable onDataLoaded) {
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                list.clear();
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String suprimento = String.valueOf(ds.child("suprimentoUUID").getValue());
//                    String pontoDistrib = String.valueOf(ds.child("pontoDistribuicaoUUID").getValue());
//                    String status = String.valueOf(ds.child("status").getValue());
//                    String data = String.valueOf(ds.child("data").getValue());
//                    IEntrega entrega = new IEntrega() {
//                    };
//                    IEntrega.suprimentoUUID = suprimento;
//                    entrega.pontoDistribuicaoUUID = pontoDistrib;
//                    entrega.status = status;
//                    entrega.data = data;
//                }
//                onDataLoaded.run();
//                Log.d("OK", "onDataChange: " + list.toString());
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                Log.d("ERROR", "onCancelled: database error " + error);
//                list.clear();
//            }
//        });
//    }
}
