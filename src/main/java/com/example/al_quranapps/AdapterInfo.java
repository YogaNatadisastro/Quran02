package com.example.al_quranapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.al_quranapps.model.InfoModel.ChapterInfo;
import com.example.al_quranapps.model.InfoModel.Info;

import java.util.List;

public class AdapterInfo extends RecyclerView.Adapter<AdapterInfo.InfoViewHolder> {

    private List<ChapterInfo> results;

    public AdapterInfo (List<ChapterInfo> results) {
        this.results = results;
    }


    @NonNull
    @Override
    public AdapterInfo.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ayat, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInfo.InfoViewHolder holder, int position) {
      ChapterInfo result = results.get(position);

      holder.textViewInfo.setText(result.getShortText());
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        TextView textViewInfo;
        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInfo = itemView.findViewById(R.id.tvAyat);
        }
    }
    public void setData(List<ChapterInfo> data) {
        results.clear();
        results.addAll(data);
        notifyDataSetChanged();
    }
}
