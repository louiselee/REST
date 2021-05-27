package com.louise.movieSiteHibernate.http;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.annotations.providers.jaxb.Wrapped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.http.entity.HttpMovie;
import com.louise.movieSiteHibernate.service.exception.MovieSiteException;
import com.louise.movieSiteHibernate.entity.impl.MovieImpl;
import com.louise.movieSiteHibernate.service.BrowsingService;


@Path("/movies")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class MovieResource {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BrowsingService BrowsingService;
	
	@POST
	@Path("/")
	public Response createMovie(HttpMovie newMovie){
		Movie movieToCreate = convert(newMovie);
		long id = BrowsingService.addMovie(movieToCreate);
		
		//return Response.status(Status.CREATED).header("Location", "/movies/"+id).entity(new HttpMovie(addedMovie)).build();
		return Response.status(Status.CREATED).header("Location", "/movies/"+id).entity(new HttpMovie(movieToCreate)).build();
	}	

	@GET
	@Path("/{movieId}")	
	public HttpMovie getMovieById(@PathParam("movieId") long movieId){
		logger.info("getting Movie by id:"+movieId);
		Movie movie = BrowsingService.getMovieById(movieId);	
		return new HttpMovie(movie);
	}
	
	@GET
	@Path("/")
	@Wrapped(element="movies")
	public List<HttpMovie> getMovieByTheaterSearch(@QueryParam("theaterName") String theaterName) throws MovieSiteException{
		logger.info("movie search by theater="+theaterName);
		@SuppressWarnings("unchecked")
		List<Movie> found = (List<Movie>)BrowsingService.getMovieList(3,theaterName);
		List<HttpMovie> returnList = new ArrayList<HttpMovie>(found.size());
		for(Movie Movie:found){
			returnList.add(new HttpMovie(Movie));
		}
		return returnList;
	}

	@GET
	@Path("/")
	@Wrapped(element="movies")
	public List<HttpMovie> getMovieByTitleSearch(@QueryParam("title") String title) throws MovieSiteException{
		logger.info("movie search by title="+ title);
		@SuppressWarnings("unchecked")
		List<Movie> found = (List<Movie>)BrowsingService.getMovieList(1,title);
		List<HttpMovie> returnList = new ArrayList<HttpMovie>(found.size());
		for(Movie Movie:found){
			returnList.add(new HttpMovie(Movie));
		}
		return returnList;
	}

	
	@GET
	@Path("/")
	@Wrapped(element="movies")
	public List<HttpMovie> getAllMoviesSearch(@QueryParam("") String Null) throws MovieSiteException{
		logger.info("movie search ");
		@SuppressWarnings("unchecked")
		List<Movie> found = (List<Movie>)BrowsingService.getMovieList(0,Null);
		List<HttpMovie> returnList = new ArrayList<HttpMovie>(found.size());
		for(Movie Movie:found){
			returnList.add(new HttpMovie(Movie));
		}
		return returnList;
	}
	
	/**
	 * Not pushing this into business layer. Could be a util as well
	 * @param newMovie
	 * @return
	 */
	private Movie convert(HttpMovie HttpMovie) {
		MovieImpl Movie = new MovieImpl();
		Movie.setTitle(HttpMovie.title);
		Movie.setTheaterName(HttpMovie.theater_name);
				return Movie;
	}	
}

