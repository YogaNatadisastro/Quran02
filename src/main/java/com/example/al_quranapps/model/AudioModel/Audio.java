package com.example.al_quranapps.model.AudioModel;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Audio {

	@SerializedName("audio_files")
	private List<AudioFilesItem> audioFiles;

	public List<AudioFilesItem> getAudioFiles(){
		return audioFiles;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"audio_files = '" + audioFiles + '\'' + 
			"}";
		}
}