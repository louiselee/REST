package com.louise.movieSiteHibernate.entity;




/**
 * A generic Ticket issued by MovieSite.com
 * @author louise
 *
 */

public interface Movie {
		        
	public String getTitle(); 
	public void setTitle(String movieTitle);
	public String getTheaterName();
    public void setTheaterName(String theaterName);	
    public String getTime(); 
	public void setTime(String time); 
	public int getUnitPrice();
	public void setUnitPrice(int unitPrice) ;
	public int getTotalPrice();
	public void setTotalPrice(int totalPrice) ;
    public boolean getBuy();
    public void setBuy(boolean buy);
	public boolean getCancel();
    public void setCancel(boolean cancel);
    public int getNumOfTickets();
    public void setNumOfTickets(int numOfTickets);
	public long getId();


}
 
        

