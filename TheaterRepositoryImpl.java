package com.louise.movieSiteHibernate.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.louise.movieSiteHibernate.entity.Theater;
import com.louise.movieSiteHibernate.entity.impl.TheaterImpl;
import com.louise.movieSiteHibernate.repository.TheaterRepository;

@Repository
public class TheaterRepositoryImpl implements TheaterRepository {	
	
	@Autowired
    private SessionFactory sessionFactory;

	// return Integer because of serialization
	public long addTheater(Theater theater) {
		return (Long) this.sessionFactory.getCurrentSession().save(theater);		
	}

	public Theater getTheater(long id) {
		return (Theater) this.sessionFactory.getCurrentSession().get(TheaterImpl.class, id);
	}
	
	public List<Theater> getAllTheaters(){	
		
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(TheaterImpl.class);
		@SuppressWarnings("unchecked")
		List<Theater> theaters= crit.list();
		
		return theaters;	
	}		
	
	public List<Theater> getTheatersByTitle(String movieTitle){	
			
			Session session = sessionFactory.getCurrentSession();
			Criteria crit = session.createCriteria(TheaterImpl.class);
			crit.add(Restrictions.like("theater", "%"+movieTitle+"%"));
			@SuppressWarnings("unchecked")
			List<Theater> theaters= crit.list();
			
			return theaters;		
	}

	public List<Theater> getTheaterByName(String theaterName) {
		
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(TheaterImpl.class);
		crit.add(Restrictions.like("theater", "%"+theaterName+"%"));
		@SuppressWarnings("unchecked")
		List<Theater> theaters= crit.list();
		
		return theaters;		
	}

	

}

