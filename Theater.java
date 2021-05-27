package com.louise.movieSiteHibernate.entity;

public interface Theater {
     long getId();
	 String getTheaterName();
	 void setTheaterName(String name);
	 int getAvailableNumberOfSeats();
	 void setAvailableNumberOfSeats(int seats);
	 long getMovieId();
}
