package com.louise.movieSiteHibernate.repository;

import java.util.List;

import com.louise.movieSiteHibernate.entity.Movie;


public interface MovieRepository {
	
	long addMovie(Movie moive);
	Movie getMovieById(long movie_id);
	List<Movie> getAllMovies();
	List<Movie> getMoviesByTitle(String title);
	List<Movie> getMoviesByTheater(String theater);
	

}
