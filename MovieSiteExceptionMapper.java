package com.louise.movieSiteHibernate.http.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Component;

import com.louise.movieSiteHibernate.service.exception.MovieSiteException;

/**
 * Return HTTP 409 with response body 
 * 
 * @author louise
 *
 */
@Provider
@Component
public class MovieSiteExceptionMapper implements ExceptionMapper<MovieSiteException>{

	public Response toResponse(MovieSiteException ex) {
		return Response.status(Status.CONFLICT).entity(new HttpError(ex)).build();
	}

	

}
