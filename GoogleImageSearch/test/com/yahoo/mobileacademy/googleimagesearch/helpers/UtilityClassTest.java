package com.yahoo.mobileacademy.googleimagesearch.helpers;

import junit.framework.TestCase;

import com.yahoo.mobileacademy.googleimagesearch.beans.SearchFilters;

/**
 * Unit tests for the public methods of the UtilityClass
 * @author CŽdric Lignier <cedric.lignier@free.fr>
 *
 */
public class UtilityClassTest extends TestCase {

	public void testFindValuePositionInArray() {
		
		String[] str = {"blue", "yellow", "black"};
		String[] strEmpty = {};
		
		assertSame("Looking up for an existing value", 0, UtilityClass.findValuePositionInArray("blue", str));
		assertSame("Looking up for an existing value", 1, UtilityClass.findValuePositionInArray("yellow", str));
		assertSame("Looking up for an existing value", 2, UtilityClass.findValuePositionInArray("black", str));
		assertSame("Looking up for a non existing value", -1, UtilityClass.findValuePositionInArray("white", str));
		assertSame("Looking up an empty array", -1, UtilityClass.findValuePositionInArray("blue", null));
		assertSame("Looking up an empty array", -1, UtilityClass.findValuePositionInArray("blue", strEmpty));

	}
	
	public void testBuildGoogleImageSearchQuery() {
		
		SearchFilters sf = new SearchFilters();
		sf.setColorFilter("blue");
		sf.setImageSize("small");
		sf.setImageType("clipart");
		sf.setSiteFilter("yahoo.com");
		
		String query = "dogs";
		
		String expectedUrl = "https://ajax.googleapis.com/ajax/services/search/images" +
				"?v=1.0" +
				"&imgcolor=blue" +
				"&imgsz=small" +
				"&imgtype=clipart" +
				"&as_sitesearch=yahoo.com" +
				"&rsz=8" +
				"&start=0" +
				"&q=dogs";
		
		assertSame("url with define search filters", expectedUrl, UtilityClass.buildGoogleImageSearchQuery(query, sf, 0));
								
	}
	


}
