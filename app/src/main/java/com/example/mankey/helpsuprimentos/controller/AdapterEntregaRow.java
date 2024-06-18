package com.example.mankey.helpsuprimentos.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mankey.helpsuprimentos.DataClass.IEntrega;
import com.example.mankey.helpsuprimentos.databinding.RowEntregaBinding;
import com.example.mankey.helpsuprimentos.viewer.GetRole;

import java.util.ArrayList;

public class AdapterEntregaRow extends RecyclerView.Adapter<AdapterEntregaRow.HolderEntrega> {
    private RowEntregaBinding binding;
    private Context context;
    private ArrayList<IEntrega> arrayList;

    public AdapterEntregaRow(Context context, ArrayList<IEntrega> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    public class HolderEntrega extends RecyclerView.ViewHolder {
        TextView tvSuprimento;
        TextView tvDistribuicao;
        TextView tvStatus;
        TextView tvData;

        public HolderEntrega(View itemView) {
            super(itemView);
            tvSuprimento = binding.tvSuprimento;
            tvDistribuicao = binding.tvDistribuicao;
            tvStatus = binding.tvStatus;
            tvData = binding.tvData;
        }
    }

    @NonNull
    @Override
    public AdapterEntregaRow.HolderEntrega onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = RowEntregaBinding.inflate(LayoutInflater.from(context), parent, false);

        return new HolderEntrega(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEntregaRow.HolderEntrega holder, int position) {
        IEntrega model = arrayList.get(position);

        holder.tvSuprimento.setText(model.suprimentoUUID);
        holder.tvDistribuicao.setText(model.pontoDistribuicaoUUID);
        holder.tvStatus.setText(model.status);
        holder.tvData.setText(model.data);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, GetRole.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
