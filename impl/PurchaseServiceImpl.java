package com.louise.movieSiteHibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louise.movieSiteHibernate.service.PurchaseService;
import com.louise.movieSiteHibernate.entity.Confirmation;
import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.entity.Revenue;

import com.louise.movieSiteHibernate.entity.impl.ConfirmationImpl;
import com.louise.movieSiteHibernate.service.CreditCardService;


@Service
public class PurchaseServiceImpl implements PurchaseService {
	
    @Autowired
	private CreditCardService creditCardService;
	
    @Autowired
	Revenue revenue;
	
    public boolean seatsAvailable(Movie movie, int numOfTickets){
             boolean more=true;
            // if (movie.getTheaterName().getAvailableNumberOfSeats() < numOfTickets)
            //  more=false;
             
             return more;
        }

	public Confirmation buyTicket(String creditId, Movie movie, int numOfTickets){
		
        Confirmation confirmation = new ConfirmationImpl(); 
      
		boolean ok=creditCardService.charge(creditId,  (numOfTickets * movie.getUnitPrice()));
               if (ok){
                  confirmation.setConfirmation(movie.getTitle()+movie.getTheaterName()+movie.getTime()+ 
                                               movie.getNumOfTickets());
                  updateRevenue( numOfTickets* movie.getUnitPrice());

                }else

                  confirmation.setConfirmation("purchase failed");
             return confirmation;
	}

	public Confirmation cancelTicket(String creditId, Movie movie, int numOfTickets){
                Confirmation confirmation = new ConfirmationImpl(); 
                boolean ok;
		ok = creditCardService.refund(creditId,numOfTickets*movie.getUnitPrice());
                if (ok) {
                	/*
                  Theater t =  movie.getTheaterName();
                  t.setAvailableNumberOfSeats(t.getAvailableNumberOfSeats()+numOfTickets);
                  confirmation.setConfirmation(" Money refunded ");
                  updateRevenue( numOfTickets* movie.getUnitPrice()* -1);
                  */
                }else
                  confirmation.setConfirmation(" cancellation failed ");
                return confirmation;
	}

       public void updateRevenue(int amount){
       revenue.add(amount);
       }
     

}

