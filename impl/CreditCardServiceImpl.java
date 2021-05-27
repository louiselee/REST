package com.louise.movieSiteHibernate.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louise.movieSiteHibernate.entity.Revenue;
import com.louise.movieSiteHibernate.service.CreditCardService;

@Service
public class CreditCardServiceImpl implements CreditCardService {

        @Autowired Revenue revenue;
	
	public boolean charge(String cardNumber,   int dollarAmount) {
         // return externalBankService.getMoney( dollarAmount, cardNumber)
		//revenue.add(dollarAmount);
		return true;
        
	}

	public boolean refund( String cardNumber, int dollarAmount) {
         // return externalBankService.setMoney(dollarAmount,  cardNumber);
		//revenue.subtract(dollarAmount);
		return true;
	}

}
