package com.example.al_quranapps.retroFIt;

import com.example.al_quranapps.model.AudioModel.Audio;
import com.example.al_quranapps.model.AyatModel.Verses;
import com.example.al_quranapps.model.IndoModel.Indo;
import com.example.al_quranapps.model.SurahModel.Chapters;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();

    @GET("quran/verses/uthmani?")
    Call<Verses> getAyat(@Query("chapter_number")int d);

    @GET("quran/translations/33?")
    Call<Indo> getArtiIndo(@Query("chapter_number") int d);

    @GET("chapter_recitations/33")
    Call<Audio> getAudioSurah();



}
