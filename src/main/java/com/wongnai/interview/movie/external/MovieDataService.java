package com.wongnai.interview.movie.external;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.Reader;

public interface MovieDataService {
	MoviesResponse fetchAll();

	JSONArray readJsonFromUrl(String url) throws IOException, JSONException;

	String readAll(Reader rd) throws IOException;

}
