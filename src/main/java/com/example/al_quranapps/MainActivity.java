package com.example.al_quranapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.al_quranapps.model.AudioModel.Audio;
import com.example.al_quranapps.model.AudioModel.AudioFilesItem;
import com.example.al_quranapps.model.SurahModel.Chapters;
import com.example.al_quranapps.model.SurahModel.ChaptersItem;
import com.example.al_quranapps.retroFIt.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private MainAdapter mainAdapter;

    private RecyclerView recyclerView;

    private List<ChaptersItem> results = new ArrayList<>();

    private List<AudioFilesItem> audioFilesItems = new ArrayList<>();

    List<ChaptersItem> surah;
    List<AudioFilesItem> audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getDataFromApi();
        setUpView();
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(results, audioFilesItems);
        RecyclerView.LayoutManager layoutManager = new  LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void getDataFromApi(){

        ApiService.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(Call<Chapters> call, Response<Chapters> response) {
                if(response.isSuccessful()){
                    MainActivity.this.surah = response.body().getChapters();
                    panggilEndpointAudio();
                }
            }

            @Override
            public void onFailure(Call<Chapters> call, Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }

    private void panggilEndpointAudio(){
        ApiService.endpoint().getAudioSurah().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(Call<Audio> call, Response<Audio> response) {
                if (response.isSuccessful()) {
                    MainActivity.this.audio = response.body().getAudioFiles();
                    mainAdapter.setData(surah, audio);
                }
            }

            @Override
            public void onFailure(Call<Audio> call, Throwable t) {

            }
        });
    }
}