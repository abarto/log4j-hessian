package org.googlecode.log4j_hessian;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

import com.caucho.services.server.ServiceContext;

/**
 * @author Agustin Barto <abarto@gmail.com>
 *
 */
public class Log4JHessianServiceImpl implements Log4JHessianService {
	private LoggerRepository loggerRepository;

	public Log4JHessianServiceImpl() {
		this(Logger.getRootLogger().getLoggerRepository());
	}

	public Log4JHessianServiceImpl(LoggerRepository loggerRepository) {
		super();
		this.loggerRepository = loggerRepository;
	}

	/* (non-Javadoc)
	 * @see org.googlecode.log4j_hessian.Log4JHessianService#append(int, org.apache.log4j.spi.LoggingEvent)
	 */
	@Override
	public void append(int level, LoggingEvent event) {
		Logger logger = loggerRepository.getLogger(event.getLoggerName());
		Level remoteLevel = Level.toLevel(level);

		if (remoteLevel.isGreaterOrEqual(logger.getEffectiveLevel())) {
			MDC.put("remoteAddr", ServiceContext.getContextRequest().getRemoteAddr());
			
			LoggingEvent loggingEvent = new LoggingEvent(
					event.getFQNOfLoggerClass(),
					logger,
					event.getTimeStamp(),
					remoteLevel,
					event.getMessage(),
					event.getThreadName(),
					event.getThrowableInformation(),
					event.getNDC(),
					event.getLocationInformation(),
					event.getProperties());

			logger.callAppenders(loggingEvent);
		}
	}
}