package com.louise.movieSiteHibernate.entity;

public interface Revenue {
	
	void add(int amount);

	void subtract(int amount);

	int getRevenue();


}
