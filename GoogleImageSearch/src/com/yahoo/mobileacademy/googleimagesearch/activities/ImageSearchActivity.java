package com.yahoo.mobileacademy.googleimagesearch.activities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.yahoo.mobileacademy.googleimagesearch.R;
import com.yahoo.mobileacademy.googleimagesearch.adapters.ImageResultArrayAdapter;
import com.yahoo.mobileacademy.googleimagesearch.helpers.UtilityClass;
import com.yahoo.mobileacademy.googleimagesearch.models.ImageResult;
import com.yahoo.mobileacademy.googleimagesearch.models.SearchFilters;

/**
 * Main application Activity
 * Provides user with a search bar and display results
 * 
 * @author CŽdric Lignier <cedric.lignier@free.fr>
 *
 */
public class ImageSearchActivity extends Activity {

	//TODO: Can be exported as constant
	private static final int REQUEST_CODE = 20;
	
	EditText etSearchQuery;
	Button btSearch, btLoadMore;
	GridView gvResults;
		
	ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
	ImageResultArrayAdapter imageAdapter;
	
	// User search filters
	SearchFilters searchFilters;
	
	// Search navigation preferences
	int currentPage = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_search);
		
		setUpViews();
		
		// Load search filters
		if (searchFilters == null) {
			searchFilters = new SearchFilters();
		}
		
		// Initialize image adapter
		imageAdapter = new ImageResultArrayAdapter(this, imageResults);
		gvResults.setAdapter(imageAdapter);
		
		// Register onItemClickLister on Grid View
		gvResults.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View parent, int position,
					long rowId) {
				
				// Create an intent and trigger the activity 
				Intent i = new Intent(getApplicationContext(), ImageDisplayActivity.class);
				ImageResult imageResult = imageResults.get(position);
				//i.putExtra("url", imageResult.getFullUrl());
				i.putExtra("result", imageResult);
				startActivity(i);
			}
			
		});
		
		// Register textChangesListener on the search query EditText View
		etSearchQuery.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	
	        	// Activate the search button only for queries
	        	// that can potentially return results
	        	btSearch.setEnabled(
	        			etSearchQuery.getText() != null
	        			&& !etSearchQuery.getText().toString().isEmpty());
	        }
	        public void beforeTextChanged(CharSequence s, int start, int count, int after){}
	        public void onTextChanged(CharSequence s, int start, int before, int count){}
	    }); 
		
	}
	
	/**
	 * Initialize object references for the View
	 */
	private void setUpViews() {
		etSearchQuery = (EditText) findViewById(R.id.etSearchQuery);
		btSearch = (Button) findViewById(R.id.btnSearch);
		gvResults = (GridView) findViewById(R.id.gvResults);
		btLoadMore = (Button) findViewById(R.id.btLoadMore);
	}
	
	// -------------------------
	// GRID VIEW RELATED METHODS
	// -------------------------

	/**
	 * Method invoked to perform an image search
	 * 
	 * NOTE: two buttons can trigger this action
	 * 	"Search" button to trigger a new search
	 *  "Load more result" button to get the next page of result
	 *  
	 * @param v the View that trigger this action
	 */
	public void onImageSearch(View v) {
		
		// If we come from the "Search" button we reinitialize the pagination to 0
		if (v.getId() == R.id.btnSearch) {
			currentPage = 0;
		}
		
		String query = etSearchQuery.getText().toString();
		
		AsyncHttpClient client = new AsyncHttpClient();
		String googleImageSearchQuery = UtilityClass.buildGoogleImageSearchQuery(query, searchFilters, currentPage);
		
		Log.d("DEBUG", googleImageSearchQuery);
		
		client.get(googleImageSearchQuery, new JsonHttpResponseHandler() {
					
					@Override
					public void onSuccess(JSONObject response) {
						JSONArray imageJsonResults = null;
						try {
							imageJsonResults = response.getJSONObject("responseData").getJSONArray("results");
							imageResults.clear();
							imageAdapter.addAll(ImageResult.fromJSONArray(imageJsonResults));
							Log.d("DEBUG", imageResults.toString());
							
							// GoogleImage API only allow 8 pages to be return
							if (currentPage < 7) {
							   btLoadMore.setEnabled(true);
							} else {
							   btLoadMore.setEnabled(false);
							}
							btLoadMore.setVisibility(Button.VISIBLE);
							btLoadMore.setOnClickListener(new OnClickListener() {
								
								@Override
								public void onClick(View v) {
									currentPage++;
									onImageSearch(v);
								}
							});
							
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
					
				});
	}
	
	// --------------------------
	// ACTION BAR RELATED METHODS
	// --------------------------

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.image_search, menu);
		return true;
	}
	
	/**
	 * Method invoked when user click on the "settings" icon
	 * from the action bar
	 * 
	 * @param mi the MenuItem
	 */
    public void onSettingsAction(MenuItem mi) {
    	
    	Intent i = new Intent(getApplicationContext(), SearchOptionsActivity.class);
    	i.putExtra("searchFilters", searchFilters);
    	startActivityForResult(i, REQUEST_CODE);
    	
    }
    
    // ------------------------
    // ACTIVITY RELATED METHODS
    // ------------------------
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    	  searchFilters = (SearchFilters) data.getSerializableExtra("searchFilters");
      }
      
    }

}
