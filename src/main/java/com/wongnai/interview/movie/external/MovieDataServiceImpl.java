package com.wongnai.interview.movie.external;

import net.minidev.json.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class MovieDataServiceImpl implements MovieDataService {
	public static final String MOVIE_DATA_URL
			= "https://raw.githubusercontent.com/prust/wikipedia-movie-data/master/movies.json";

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public MoviesResponse fetchAll() {
		MoviesResponse movieJsonArray = new MoviesResponse();
		try {
			JSONArray jsonArray = readJsonFromUrl(MOVIE_DATA_URL);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject subJsonObj = jsonArray.getJSONObject(i);
				MovieData movieData = new MovieData();
				movieData.setTitle(subJsonObj.getString("title"));
				movieData.setYear(subJsonObj.getInt("year"));
				if (subJsonObj.has("cast")) {
					JSONArray subJsonArray = subJsonObj.getJSONArray("cast");
					List<String> list = new ArrayList<>();
					for(int j=0; j < subJsonArray.length(); j++) {
						list.add(subJsonArray.getString(j));
					}
					movieData.setCast(list);
				}
				if (subJsonObj.has("genres")) {
					JSONArray subJsonArray = subJsonObj.getJSONArray("genres");
					List<String> list = new ArrayList<>();
					for(int j=0; j < subJsonArray.length(); j++) {
						list.add(subJsonArray.getString(j));
					}
					movieData.setGenres(list);
				}
				movieJsonArray.add(movieData);
			}
		} catch (JSONException | IOException e) {
			e.printStackTrace();
			return null;
		}
		return movieJsonArray;
	}

	private JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
		try (InputStream is = new URL(url).openStream()) {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			return new JSONArray(jsonText);
		}
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}
}
