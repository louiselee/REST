package com.louise.movieSiteHibernate.service;


public interface CreditCardService {

     
	public boolean charge(String cardNumber, int dollarAmount );
	
	public boolean refund(String cardNumber, int dollarAmount);

    

}
