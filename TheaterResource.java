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

import com.louise.movieSiteHibernate.entity.Theater;
import com.louise.movieSiteHibernate.http.entity.HttpTheater;
import com.louise.movieSiteHibernate.service.exception.MovieSiteException;
import com.louise.movieSiteHibernate.entity.impl.TheaterImpl;
import com.louise.movieSiteHibernate.service.BrowsingService;


@Path("/theaters")
@Component
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public class TheaterResource {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BrowsingService BrowsingService;
	
	@POST
	@Path("/")
	public Response createTheater(HttpTheater newTheater){
		Theater theaterToCreate = convert(newTheater);
		long id = BrowsingService.addTheater(theaterToCreate);
		
		//return Response.status(Status.CREATED).header("Location", "/movies/"+id).entity(new HttpMovie(addedMovie)).build();
		return Response.status(Status.CREATED).header("Location", "/theaters/"+id).entity(new HttpTheater(theaterToCreate)).build();
	}	

	@GET
	@Path("/{theaterName}")	
	public List<HttpTheater> getTheaterByName(@PathParam("theaterName") String theaterName){
		logger.info("getting Theater by name:"+ theaterName);
		List<Theater> theaters = BrowsingService.getTheaterByName(theaterName);	
		List<HttpTheater> returnList = new ArrayList<HttpTheater>(theaters.size());
		for(Theater theater:theaters){
			returnList.add(new HttpTheater(theater));
		}	
		return returnList;
	}
	
	
	@GET
	@Path("/")
	@Wrapped(element="theaters")
	public List<HttpTheater> getTheaterByMovieSearch(@QueryParam("movieTitle") String movieTitle) throws MovieSiteException{
		logger.info("theater search by movie="+ movieTitle);
		@SuppressWarnings("unchecked")
		List<Theater> found = (List<Theater>)BrowsingService.getMovieList(4,movieTitle); //getMovieList is generic 
		List<HttpTheater> returnList = new ArrayList<HttpTheater>(found.size());
		for(Theater theater:found){
			returnList.add(new HttpTheater(theater));
		}
		return returnList;
	}

	@GET
	@Path("/")
	@Wrapped(element="theater")
	public List<HttpTheater> getTheaterByTheaterName(@QueryParam("theaterName") String theaterName) throws MovieSiteException{
		logger.info("theater search by name="+ theaterName);
		@SuppressWarnings("unchecked")
		List<Theater> found = (List<Theater>)BrowsingService.getMovieList(2,theaterName);
		List<HttpTheater> returnList = new ArrayList<HttpTheater>(found.size());
		for(Theater theater:found){
			returnList.add(new HttpTheater(theater));
		}
		return returnList;
	}

	
	@GET
	@Path("/")
	@Wrapped(element="theaters")
	public List<HttpTheater> getAllTheaterSearch(@QueryParam("") String theaterName) throws MovieSiteException{
		logger.info("theater search ");
		@SuppressWarnings("unchecked")
		List<Theater> found = (List<Theater>)BrowsingService.getMovieList(9,null);
		List<HttpTheater> returnList = new ArrayList<HttpTheater>(found.size());
		for(Theater theater:found){
			returnList.add(new HttpTheater(theater));
		}
		return returnList;
	}
	
	/**
	 * Not pushing this into business layer. Could be a util as well
	 * @param newMovie
	 * @return
	 */
	private Theater convert(HttpTheater httpTheater) {
		TheaterImpl theater = new TheaterImpl();
		theater.setTheaterName(httpTheater.theater_name);
		theater.setId(httpTheater.theater_id);
	    return theater;
	}	
}

