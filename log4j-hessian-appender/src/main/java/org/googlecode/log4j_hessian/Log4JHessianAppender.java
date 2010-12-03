package org.googlecode.log4j_hessian;

import java.net.MalformedURLException;

import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * @author Agustin Barto <abarto@gmail.com>
 *
 */
public class Log4JHessianAppender extends AppenderSkeleton implements Appender {
	private Log4JHessianService log4jHessianService;
	private String url;
	private boolean locationInfo = false;
	private String application;

	@Override
	public void close() {
	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected void append(LoggingEvent event) {
	    event.getNDC();
	    event.getThreadName();
	    event.getMDCCopy();
	    
	    if (locationInfo) {
	    	event.getLocationInformation();
	    }
	    
	    if (application != null && !"".equals(application)) {
	    	event.setProperty("application", application);
	    }

	    event.getRenderedMessage();
	    event.getThrowableStrRep();
	    
	    log4jHessianService.append(event.getLevel().toInt(), event);
	}
	

	@Override
	public void activateOptions() {
		super.activateOptions();
		
		try {
			HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
			log4jHessianService = (Log4JHessianService) hessianProxyFactory.create(Log4JHessianService.class, url);
		} catch (MalformedURLException e) {
			e.fillInStackTrace();
			throw new IllegalArgumentException(e);
		}
	}	

	public boolean getLocationInfo() {
		return locationInfo;
	}

	public void setLocationInfo(boolean locationInfo) {
		this.locationInfo = locationInfo;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}
}