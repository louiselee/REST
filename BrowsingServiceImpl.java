package com.louise.movieSiteHibernate.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louise.movieSiteHibernate.entity.Confirmation;
import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.entity.Theater;

import com.louise.movieSiteHibernate.repository.MovieRepository;
import com.louise.movieSiteHibernate.repository.TheaterRepository;

import com.louise.movieSiteHibernate.service.BrowsingService;
import com.louise.movieSiteHibernate.service.PurchaseService;

@Service
public class BrowsingServiceImpl implements BrowsingService {

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	public MovieRepository movieRepository;
	
	@Autowired
	public TheaterRepository theaterRepository;
	
	@Transactional
	public long addMovie(Movie movie){
	
	    long m= movieRepository.addMovie(movie);
		return m;
	}
	
	@Transactional
	public long addTheater(Theater theater){
	
	    long m= theaterRepository.addTheater(theater);
		return m;
	}
	
	
	@Transactional
	public Movie getMovieById(long id){
	
	    Movie m= movieRepository.getMovieById(id);
		return m;
	}
	
	
	@Transactional
	public List<Theater> getTheaterByName(String theaterName){
	
	    List<Theater> m= theaterRepository.getTheaterByName(theaterName);
		return m;
	}
	
	
	@Transactional
	public List<?> getMovieList(int type, String name){
		
		//ArrayList <Movie> a= new ArrayList<Movie>(10);  // to retrieve from database
		
		@SuppressWarnings({ "rawtypes" })
		List list = null;
		
		
		// 0,null => all movies  (movieResource)
	    // 1,String => select movies by title   (movieResource)
		// 3,String => all movies in a theater  (movieResource)
  	
		// 0,String => all theaters  (theaterResource)
		// 2,String => select theater by theaterName   (theaterResource)
		// 4,String => all theaters playing the movie  (theaterResource)
	
	
		if ((type==0) && (name==null)) {
		  list = movieRepository.getAllMovies();
		}else if ((type==0) && (name != null)) {
		  list = theaterRepository.getAllTheaters();
		}else if ((type==1) && (name != null)) {
		  list = movieRepository.getMoviesByTitle(name);     
		}else if ((type==3) && (name != null)) {
		  list = movieRepository.getMoviesByTheater(name);
		}else if ((type==4) && (name != null)) {
		  list = movieRepository.getMoviesByTheater(name);
		}else {
			//error message
		}
		
		return list;
	}

	public Confirmation buyTicket(String cardNumber,Movie movie, int numOfTickets) {
          Confirmation t =        
                 purchaseService.buyTicket(cardNumber,movie,numOfTickets);
		
          return t;
	}

	public Confirmation cancelTicket(String cardNumber,Movie movie, int numOfTickets) {
          Confirmation t =  purchaseService.cancelTicket(cardNumber,movie,numOfTickets);
		
          return t;
	}
}
