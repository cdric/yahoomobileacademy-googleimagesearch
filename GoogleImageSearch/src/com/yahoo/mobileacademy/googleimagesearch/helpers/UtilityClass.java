package com.yahoo.mobileacademy.googleimagesearch.helpers;

import java.util.Arrays;
import java.util.List;

import android.net.Uri;

import com.yahoo.mobileacademy.googleimagesearch.models.SearchFilters;

/**
 * Generic utility class that contain static
 * methods related to this project
 * 
 * @author CŽdric Lignier <cedric.lignier@free.fr>
 *
 */
public class UtilityClass {

	/**
	 * Build the GoogleImageSearch query based on the user query and SearchFilters
	 * @param query the query from the user
	 * @param searchFilters the searchFilters preference from the user
	 * @param pageIndex the index of the page to return
	 * @return Return a formatter query to the Google Search API
	 * 
	 * Documentation:
	 * https://developers.google.com/image-search/v1/jsondevguide#json_reference
	 */
	public static String buildGoogleImageSearchQuery(String query,
			SearchFilters searchFilters,
			int pageIndex) {
		
		int pageSize = 8;
		
		StringBuffer searchQuery = new StringBuffer();
		searchQuery.append("https://ajax.googleapis.com/ajax/services/search/images?");
		searchQuery.append("v=" + "1.0");
		
		if (searchFilters != null) {
			if (searchFilters.getColorFilter() != null) {
				searchQuery.append("&imgcolor=" + searchFilters.getColorFilter());
			}
			if (searchFilters.getImageSize() != null) {
				searchQuery.append("&imgsz=" + searchFilters.getImageSize());
			}
			if (searchFilters.getImageType() != null) {
				searchQuery.append("&imgtype=" + searchFilters.getImageType());
			}
			if (searchFilters.getSiteFilter() != null) {
				searchQuery.append("&as_sitesearch=" + Uri.encode(searchFilters.getSiteFilter()));
			}
		}
		
		searchQuery.append("&rsz=" + pageSize);
		searchQuery.append("&start=" + (pageSize * pageIndex));
		searchQuery.append("&q=" + Uri.encode(query));
		
		return searchQuery.toString();
	}
	
	/**
	 * Return the position of an element in an array (0...n)
	 * Return -1 if the element does not exist
	 * 
	 * @param element the element to look for
	 * @param array the array to parse
	 * @return
	 */
	public static int findValuePositionInArray(String element,
			String[] array) {
		
		if (array == null || array.length == 0) return -1;
		
		List<String> list = Arrays.asList(array);
		
		if (list.contains(element)) {
			return list.indexOf(element);
		} else {
			return -1;
		}
		
	}
	
}
