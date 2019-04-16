package com.wongnai.interview.movie.sync;

import javax.transaction.Transactional;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.external.MovieDataService;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieDataSynchronizer {
	@Autowired
	private MovieDataService movieDataService;

	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public void forceSync() {
		//TODO: implement this to sync movie into repository
		MoviesResponse allMovies = movieDataService.fetchAll();
		List<Movie> result = new ArrayList<>();
		for (MovieData element : allMovies) {
			Movie movie = new Movie(element.getTitle());
			if(element.getCast().size() != 0)
				movie.setActors(element.getCast());
			result.add(movie);
		}
		movieRepository.saveAll(result);
	}
}
