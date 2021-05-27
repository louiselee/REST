package com.louise.movieSiteHibernate.http.entity;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.louise.movieSiteHibernate.entity.Movie;
import com.louise.movieSiteHibernate.http.entity.HttpMovie;

/**
 * Select fields we want exposed to the REST layer. Separation from business/data layer. 
 * 
 * Note "XML" annotation. The resteasy-jettison implementation converts these to JSON depending on
 * the Accept media type
 * 
 * @author louise
 *
 */
@XmlRootElement(name = "movie")
public class HttpMovie {
	
	@XmlElement
	public long id;
	
	@XmlElement
	public String title;
	
	@XmlElement
	public String theater_name;
	
	@XmlElement
	public String pin;
	
	//required by framework
	protected HttpMovie() {}

	public HttpMovie(Movie movie) {
		this.id=movie.getId();
		this.title=movie.getTitle();
		this.theater_name=movie.getTheaterName();
		
	}
	
}
