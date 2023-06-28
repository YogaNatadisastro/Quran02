package com.example.al_quranapps.model.InfoModel;

import com.google.gson.annotations.SerializedName;

public class Info {

	@SerializedName("chapter_info")
	private ChapterInfo chapterInfo;

	public ChapterInfo getChapterInfo(){
		return chapterInfo;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"chapter_info = '" + chapterInfo + '\'' + 
			"}";
		}
}