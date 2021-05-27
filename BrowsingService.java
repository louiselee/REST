package com.louise.movieSiteHibernate.service;

import java.util.List;

import com.louise.movieSiteHibernate.entity.Confirmation;
import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.entity.Theater;


public interface BrowsingService {
	
	long addMovie(Movie movie);
	long addTheater(Theater theater);

	Movie getMovieById(long id);
	List<Theater> getTheaterByName(String name);
	
	// 0,null => all movies, 9,null => all theaters
	// 1,String => select movies by movie title, 2,String => select theater by name
    // 3,String => all movies in a theater, 4,String => all theaters playing the movie
	List<?> getMovieList(int type, String name);
	
	
	Confirmation buyTicket(String cardNumber,Movie movie, int numOfTicket);
	
	Confirmation cancelTicket(String cardNumber,Movie movie, int numOfTicket);
	
	
}
