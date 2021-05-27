package com.louise.movieSiteHibernate.repository;

import java.util.List;

import com.louise.movieSiteHibernate.entity.Theater;


public interface TheaterRepository {
	
	long addTheater(Theater theater);
	
	List<Theater> getAllTheaters();
	List<Theater> getTheaterByName(String name);
	List<Theater> getTheatersByTitle(String movieTitle);
	Theater getTheater(long id);

}
