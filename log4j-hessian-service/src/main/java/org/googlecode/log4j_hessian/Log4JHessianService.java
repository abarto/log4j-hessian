package org.googlecode.log4j_hessian;

import org.apache.log4j.spi.LoggingEvent;

/**
 * @author Agustin Barto <abarto@gmail.com>
 *
 */
public interface Log4JHessianService {
	/**
	 * @param level
	 * @param remoteEvent
	 */
	void append(int level, LoggingEvent remoteEvent);
}
