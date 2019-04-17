package com.wongnai.interview.movie.search;

import java.util.*;

import com.wongnai.interview.movie.external.MovieData;
import com.wongnai.interview.movie.external.MovieDataService;
import com.wongnai.interview.movie.external.MoviesResponse;
import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.wongnai.interview.movie.Movie;
import com.wongnai.interview.movie.MovieRepository;
import com.wongnai.interview.movie.MovieSearchService;

@Component("invertedIndexMovieSearchService")
@DependsOn("movieDatabaseInitializer")
public class InvertedIndexMovieSearchService implements MovieSearchService {
	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieDataService movieDataService;

	@Override
	public List<Movie> search(String queryText) {
		//TODO: Step 4 => Please implement in-memory inverted index to search movie by keyword.
		// You must find a way to build inverted index before you do an actual search.
		// Inverted index would looks like this:
		// -------------------------------
		// |  Term      | Movie Ids      |
		// -------------------------------
		// |  Star      |  5, 8, 1       |
		// |  War       |  5, 2          |
		// |  Trek      |  1, 8          |
		// -------------------------------
		// When you search with keyword "Star", you will know immediately, by looking at Term column, and see that
		// there are 3 movie ids contains this word -- 1,5,8. Then, you can use these ids to find full movie object from repository.
		// Another case is when you find with keyword "Star War", there are 2 terms, Star and War, then you lookup
		// from inverted index for Star and for War so that you get movie ids 1,5,8 for Star and 2,5 for War. The result that
		// you have to return can be union or intersection of those 2 sets of ids.
		// By the way, in this assignment, you must use intersection so that it left for just movie id 5.
//
//		Map<String, List<Integer>> moviesMap = new HashMap<>();
//		MoviesResponse allMovies = movieDataService.fetchAll();
//		List<Integer> list1 = new ArrayList<>();
//		List<Integer> list2 = new ArrayList<>();
//		List<Integer> list3 = new ArrayList<>();
//		List<Movie> result = new ArrayList<>();
//		int index = 0;
//		if(queryText.equals("Star"))
//		for (MovieData movie : allMovies) {
//			String [] words = movie.getTitle().split(" ", 10);
//			for (String word : words) {
//				switch(word.toLowerCase()) {
//					case "star":
//						list1.add(index);
//						break;
//					case "war":
//						list2.add(index);
//						break;
//					case "trek":
//						list3.add(index);
//						break;
//					default:
//						if (word.toLowerCase().equals(queryText.toLowerCase())) {
//							Movie other = new Movie(movie.getTitle());
//							if(movie.getCast().size() != 0)
//								other.setActors(movie.getCast());
//							result.add(other);
//							break;
//						}
//				}
//			}
//			index++;
//		}
//		if(!list1.isEmpty()) {
//			moviesMap.put("Star", list1);
//		}else if(!list2.isEmpty()) {
//			moviesMap.put("War", list2);
//		}else if(!list3.isEmpty()) {
//			moviesMap.put("War", list3);
//		}
//		List<Integer> value = moviesMap.get("Star");
//		System.out.println(value);
		return null;
	}
}
