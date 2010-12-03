package org.googlecode.log4j_hessian;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;

/**
 * @author Agustin Barto <abarto@gmail.com>
 *
 */
public class Log4JHessianAppenderTest {
	private static final Logger logger = Logger.getLogger(Log4JHessianAppenderTest.class);
	
	public static void main(String[] args) {
		for (int i = 0 ; i < 10 ; i++) {
			logger.info(RandomStringUtils.randomAlphanumeric(64));
		}
		
		for (int i = 0 ; i < 10 ; i++) {
			logger.warn(RandomStringUtils.randomAlphanumeric(64));
		}

		for (int i = 0 ; i < 10 ; i++) {
			logger.error(RandomStringUtils.randomAlphanumeric(64));
		}

		for (int i = 0 ; i < 10 ; i++) {
			logger.debug(RandomStringUtils.randomAlphanumeric(64));
		}
	}
}
