package com.louise.movieSiteHibernate.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.entity.impl.MovieImpl;
import com.louise.movieSiteHibernate.repository.MovieRepository;

@Repository
public class MovieRepositoryImpl implements MovieRepository {	
	
	@Autowired
    private SessionFactory sessionFactory;

	// return Integer because of serialization
	public long addMovie(Movie movie) {
		return (Long) this.sessionFactory.getCurrentSession().save(movie);		
	}

	public Movie getMovieById(long id) {
		return (Movie) this.sessionFactory.getCurrentSession().get(MovieImpl.class, id);
	}
	
	public List<Movie> getAllMovies(){	
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(MovieImpl.class);
		@SuppressWarnings("unchecked")
		List<Movie> movies= crit.list();
		
		return movies;	
	}		
	
	public List<Movie> getMoviesByTitle(String title){	
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(MovieImpl.class);
		crit.add(Restrictions.like("title", "%"+title+"%"));
		@SuppressWarnings("unchecked")
		List<Movie> movies= crit.list();
		
		return movies;		
	}

	public List<Movie> getMoviesByTheater(String theater) {
		Session session = sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(MovieImpl.class);
		crit.add(Restrictions.like("theater_name", "%"+theater+"%"));
		@SuppressWarnings("unchecked")
		List<Movie> movies= crit.list();
		return movies;
	}		
}


