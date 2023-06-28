package com.example.al_quranapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranapps.model.AyatModel.Verses;
import com.example.al_quranapps.model.AyatModel.VersesItem;
import com.example.al_quranapps.model.IndoModel.TranslationsItem;



import java.util.ArrayList;
import java.util.List;

public class AdapterAyats extends RecyclerView.Adapter<AdapterAyats.AyatViewHolder> {
    private List<VersesItem> results;
    private List<TranslationsItem> arti;


    public AdapterAyats (List<VersesItem> results, List<TranslationsItem> arti ){
        this.results = results;
        this.arti = arti;
    }


    @NonNull
    @Override
    public AdapterAyats.AyatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAyats.AyatViewHolder holder, int position) {
        VersesItem result = results.get(position);
        TranslationsItem rest = arti.get(position);
        holder.textViewAyat.setText(result.getTextUthmani());
        holder.textViewTerjemah.setText(rest.getText());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAyat, textViewNomorAyat, textViewTerjemah;
        public AyatViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAyat = itemView.findViewById(R.id.tvAyat);
            textViewTerjemah = itemView.findViewById(R.id.tvTerjemahanAyat);
            textViewNomorAyat = itemView.findViewById(R.id.tvNomorAyat);
        }
    }

    public void setData(List<VersesItem> data, List<TranslationsItem> data2) {
        results.clear();
        results.addAll(data);

        arti.clear();
        arti.addAll(data2);
        notifyDataSetChanged();
    }

}

