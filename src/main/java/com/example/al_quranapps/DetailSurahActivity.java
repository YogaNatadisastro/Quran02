package com.example.al_quranapps;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;


import com.example.al_quranapps.model.AyatModel.Verses;
import com.example.al_quranapps.model.AyatModel.VersesItem;
import com.example.al_quranapps.model.IndoModel.Indo;
import com.example.al_quranapps.model.IndoModel.TranslationsItem;
import com.example.al_quranapps.retroFIt.ApiService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailSurahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterAyats adapterAyats;
    private List<VersesItem> listAyat = new ArrayList<>();
    private List<TranslationsItem> listTerjemahan = new ArrayList<>();

    Button tvAudio;


    List<TranslationsItem> terjemah;
    List<VersesItem> versesAyat;


    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_surah);

        int id = getIntent().getIntExtra("id", 1);

        mediaPlayer = new MediaPlayer();
        String audioURL = getIntent().getStringExtra("audioURL");
        tvAudio = findViewById(R.id.tvAudio);
        tvAudio.setOnClickListener(view -> {
            if (mediaPlayer.isPlaying()){
                pause();
            } else {
                play(audioURL);
            }

        });

        setUpView();
        setUpRecyclerView();
        getDataFromApi(id);
    }

    private void play(String sound) {
        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(sound);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException i){
            i.printStackTrace();
        }
    }

    private void pause() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    private void setUpRecyclerView() {
        adapterAyats = new AdapterAyats(listAyat, listTerjemahan);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAyats);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerViewAyat);
    }

    private void getDataFromApi(int id) {

        ApiService.endpoint().getAyat(id).enqueue(new Callback<Verses>() {
            @Override
            public void onResponse(Call<Verses> call, Response<Verses> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.versesAyat = response.body().getVerses();
                    panggilEndpointTerjemah(getIntent().getIntExtra("id", 1));
                }
            }
            @Override
            public void onFailure(Call<Verses> call, Throwable t) {
                Log.d("Ayat", t.toString());
            }
        });
    }

    private void panggilEndpointTerjemah(int id){
        ApiService.endpoint().getArtiIndo(id).enqueue(new Callback<Indo>() {
            @Override
            public void onResponse(Call<Indo> call, Response<Indo> response) {
                if (response.isSuccessful()) {
                    DetailSurahActivity.this.terjemah = response.body().getTranslations();
                    adapterAyats.setData(versesAyat, terjemah);
                }
            }

            @Override
            public void onFailure(Call<Indo> call, Throwable t) {
            }
        });
    }

    }
