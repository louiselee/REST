package com.louise.movieSiteHibernate.util;

import javax.servlet.ServletContext;

import org.jboss.resteasy.plugins.spring.SpringContextLoaderSupport;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;

/**
 * REST Easy/Spring 4 issue workaround - see here https://issues.jboss.org/browse/RESTEASY-1012
 * Hope to remove this in the future
 * 
 * @author louise
 *
 */
public class CustomContextLoaderListener extends ContextLoaderListener {
	private SpringContextLoaderSupport springContextLoaderSupport = new SpringContextLoaderSupport();

	@Override
	protected void customizeContext(ServletContext servletContext, ConfigurableWebApplicationContext configurableWebApplicationContext) {	
		super.customizeContext(servletContext, configurableWebApplicationContext);		
		this.springContextLoaderSupport.customizeContext(servletContext, configurableWebApplicationContext);
	}
}