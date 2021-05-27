package com.louise.movieSiteHibernate.service;

import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.entity.Confirmation;

//BoxOffice
public interface PurchaseService {

       
       boolean seatsAvailable(Movie movie, int numOfTickets);

       Confirmation buyTicket(String creditId, Movie movie, int numOfSeats);

       Confirmation cancelTicket(String creditId, Movie movie, int numOfSeats);
      
       void updateRevenue(int amount);

       //void customerService(String msg);



}
