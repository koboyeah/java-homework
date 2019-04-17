package com.wongnai.interview.movie.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieSearchService;
import com.wongnai.interview.movie.external.MovieDataService;

@Component("simpleMovieSearchService")
public class SimpleMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
    private MovieRepository movieRepository;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 2 => Implement this method by using data from MovieDataService
		// All test in SimpleMovieSearchServiceIntegrationTest must pass.
		// Please do not change @Component annotation on this class
        MoviesResponse allMovies = movieDataService.fetchAll();
        List<Movie> result = new ArrayList<>();
        for (MovieData element : allMovies) {
            String [] words = element.getTitle().split(" ", 10);
            for (String word : words) {
                if (word.toLowerCase().equals(queryText.toLowerCase())) {
                    Movie movie = new Movie(element.getTitle());
                    if(element.getCast().size() != 0)
                        movie.setActors(element.getCast());
                    result.add(movie);
                    break;
                }
            }
        }

		return result;
	}
}
