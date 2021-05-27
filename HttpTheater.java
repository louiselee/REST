package com.louise.movieSiteHibernate.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


import com.louise.movieSiteHibernate.entity.Theater;


/**
 * Select fields we want exposed to the REST layer. Separation from business/data layer. 
 * 
 * Note "XML" annotation. The resteasy-jettison implementation converts these to JSON depending on
 * the Accept media type
 * 
 * @author louise
 *
 */
@XmlRootElement(name = "theater")
public class HttpTheater {
	
	@XmlElement
	public long theater_id;
		
	@XmlElement
	public String theater_name;
	
	@XmlElement
	public long movie_id;
	
    //required by framework
	protected HttpTheater() {}

	public HttpTheater(Theater theater) {
		this.theater_id=theater.getId();
		this.movie_id=theater.getMovieId();
		this.theater_name=theater.getTheaterName();
		
	}
}
