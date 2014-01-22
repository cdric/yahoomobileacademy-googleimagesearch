package com.yahoo.mobileacademy.googleimagesearch.activities;

import android.app.Activity;
import android.os.Bundle;

import com.loopj.android.image.SmartImageView;
import com.yahoo.mobileacademy.googleimagesearch.R;
import com.yahoo.mobileacademy.googleimagesearch.R.id;
import com.yahoo.mobileacademy.googleimagesearch.R.layout;
import com.yahoo.mobileacademy.googleimagesearch.models.ImageResult;

/**
 * Activity that displays a full screen image
 * 
 * @author CŽdric Lignier <cedric.lignier@free.fr>
 *
 */
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
