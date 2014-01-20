package com.yahoo.mobileacademy.googleimagesearch;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;
import com.yahoo.mobileacademy.googleimagesearch.beans.ImageResult;

public class ImageDisplayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_display);
	
		// Fetching data that are passed to this activity
		ImageResult imageResult = (ImageResult) getIntent().getSerializableExtra("result");
		SmartImageView ivImage = (SmartImageView) findViewById(R.id.ivResult);
		ivImage.setImageUrl(imageResult.getFullUrl());
	
	}

}
