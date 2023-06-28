package com.example.al_quranapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.al_quranapps.model.IndoModel.TranslationsItem;

import java.util.List;

public class AdapterTerjemahan extends RecyclerView.Adapter<AdapterTerjemahan.TerjemehanHolder> {
    private List<TranslationsItem> results;

    public AdapterTerjemahan (List<TranslationsItem> results) {
        this.results = results;
    }


    @NonNull
    @Override
    public AdapterTerjemahan.TerjemehanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TerjemehanHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTerjemahan.TerjemehanHolder holder, int position) {
        TranslationsItem result = results.get(position);

        holder.textViewTerjemahan.setText(result.getText());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class TerjemehanHolder extends RecyclerView.ViewHolder {

        TextView textViewTerjemahan;
        public TerjemehanHolder(@NonNull View itemView) {
            super(itemView);
            textViewTerjemahan = itemView.findViewById(R.id.tvTerjemahanSurah);
        }
    }
    public void setData(List<TranslationsItem> data){
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}
