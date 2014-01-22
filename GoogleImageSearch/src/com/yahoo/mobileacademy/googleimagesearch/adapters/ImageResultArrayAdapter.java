package com.yahoo.mobileacademy.googleimagesearch.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.image.SmartImageView;
import com.yahoo.mobileacademy.googleimagesearch.R;
import com.yahoo.mobileacademy.googleimagesearch.models.ImageResult;

public class ImageResultArrayAdapter extends ArrayAdapter<ImageResult> {

	public ImageResultArrayAdapter(Context context, List<ImageResult> images) {
		super(context, R.layout.item_image_results, images);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = this.getItem(position);
		SmartImageView ivImage;
		
		// Either re-utilize the view if it's already exist
		// Or create a new one
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(getContext());
			ivImage = (SmartImageView) inflator.inflate(R.layout.item_image_results, parent, false);
		} else {
			ivImage = (SmartImageView) convertView;
			ivImage.setImageResource(android.R.color.transparent);
		}
		
		// Define the imageUrl property from the result we fetch from the Google API
		ivImage.setImageUrl(imageInfo.getThumbUrl());
		return ivImage;
	}

}
