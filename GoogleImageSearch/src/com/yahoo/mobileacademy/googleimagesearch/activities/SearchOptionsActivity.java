package com.yahoo.mobileacademy.googleimagesearch.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.yahoo.mobileacademy.googleimagesearch.R;
import com.yahoo.mobileacademy.googleimagesearch.R.array;
import com.yahoo.mobileacademy.googleimagesearch.R.id;
import com.yahoo.mobileacademy.googleimagesearch.R.layout;
import com.yahoo.mobileacademy.googleimagesearch.helpers.UtilityClass;
import com.yahoo.mobileacademy.googleimagesearch.models.SearchFilters;

/**
 * Advanced Search Filter Activity
 * Provide various filters for the user to improve
 * the relevance of the search results
 * 
 * @author CŽdric Lignier <cedric.lignier@free.fr>
 *
 */
public class SearchOptionsActivity extends Activity {

	private SearchFilters searchFilters = new SearchFilters();
	
	private Spinner spImageSize;
	private Spinner spColorFilter;
	private Spinner spImageType;
	private EditText etSiteFilter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_options);
		setUpViews();
		loadSearchFilters();
	}

	/**
	 * Setup the views for this activity
	 */
	private void setUpViews() {
		spImageSize = initSpinner(R.id.spImageSize, R.array.image_size_values);
		spColorFilter = initSpinner(R.id.spColorFilter, R.array.color_filter_values);
		spImageType = initSpinner(R.id.spImageType, R.array.image_type_values);
		etSiteFilter = (EditText) findViewById(R.id.etSiteFilter);
	}
	
	/**
	 * Initialize a spinner to have it's TextView to display text align to the right
	 * @param spinnerId the ref to the ID for the spinner 
	 * @param spinnerValues the ref to the ID of the array of resources for the spinner
	 * @return Returns the Spinner object
	 */
	private Spinner initSpinner(int spinnerId, int spinnerValues) {
		Spinner spinner = (Spinner) findViewById(spinnerId);
		
		// Update the layout of the spinner to align text to the right
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	    		this, 
	    		spinnerValues, 
	    		R.layout.spinner_textview);
	    spinner.setAdapter(adapter);
	    
	    return spinner;
	}
	
	/**
	 * Method invoked when the user click the "Save" button
	 * @param v
	 */
	public void onClickSave(View v) {
		Intent i = new Intent(getApplicationContext(), ImageSearchActivity.class);
		updateSearchFilters();
		i.putExtra("searchFilters", searchFilters);
		startActivity(i);
	}

	/**
	 * Update the searchFilters based on user selection
	 */
	private void updateSearchFilters() {
		searchFilters.setImageSize(spImageSize.getSelectedItem().toString());
		searchFilters.setColorFilter(spColorFilter.getSelectedItem().toString());
		searchFilters.setImageType(spImageType.getSelectedItem().toString());
		searchFilters.setSiteFilter(etSiteFilter.getText().toString());
	}
	
	/**
	 * Initialized the page with the current SearchFilters object
	 */
	private void loadSearchFilters() {
		searchFilters = (SearchFilters) getIntent().getSerializableExtra("searchFilters");
		if (searchFilters != null) {
			// Color Filter
			if (searchFilters.getColorFilter() != null) {
				spColorFilter.setSelection(UtilityClass.findValuePositionInArray(searchFilters.getColorFilter(), getResources().getStringArray(R.array.color_filter_values)));
			}
			// Image Size Filter
			if (searchFilters.getImageSize() != null) {
				spImageSize.setSelection(UtilityClass.findValuePositionInArray(searchFilters.getImageSize(), getResources().getStringArray(R.array.image_size_values)));
			}
			// Image Type Filter
			if (searchFilters.getImageType() != null) {
				spImageType.setSelection(UtilityClass.findValuePositionInArray(searchFilters.getImageType(), getResources().getStringArray(R.array.image_type_values)));
			}
			// Site Filter
			if (searchFilters.getSiteFilter() != null) {
				etSiteFilter.setText(searchFilters.getSiteFilter());
			}
		}
	}

}
