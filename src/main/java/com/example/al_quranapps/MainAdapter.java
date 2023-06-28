package com.example.al_quranapps;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranapps.model.AudioModel.AudioFilesItem;
import com.example.al_quranapps.model.SurahModel.ChaptersItem;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<ChaptersItem> results = new ArrayList<>();

    private List<AudioFilesItem> audioFilesItems = new ArrayList<>();

    public MainAdapter(List<ChaptersItem> results, List<AudioFilesItem> audioFilesItems) {
        this.results = results;
        this.audioFilesItems = audioFilesItems;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false );

        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        ChaptersItem chapters = results.get(position);
        AudioFilesItem audio = audioFilesItems.get(position);

        holder.textViewSurahLatin.setText(chapters.getNameSimple());
        holder.textViewTerjemahanSurah.setText(chapters.getTranslatedName().getName());
        holder.textViewSurahArab.setText(chapters.getNameArabic());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);
                intent.putExtra("id", chapters.getId());
                intent.putExtra("audioURL", audio.getAudioUrl());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewTerjemahanSurah, textViewSurahArab;
        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);

        }
    }

    public  void setData(List<ChaptersItem> surah, List<AudioFilesItem> audio){
        results.clear();
        results.addAll(surah);

        audioFilesItems.clear();
        audioFilesItems.addAll(audio);
        notifyDataSetChanged();
    }

}
