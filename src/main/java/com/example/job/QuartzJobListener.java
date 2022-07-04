package com.example.job;

import static net.logstash.logback.argument.StructuredArguments.kv;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJobListener implements JobListener {
	private static final Logger logger = LoggerFactory.getLogger(QuartzJobListener.class);
	private static final String LISTENER_NAME = "QuartzJobListenerName";

	@Override
	public String getName() {
		return LISTENER_NAME;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		logger.info("Job Execution Vetoed: "+jobName);
		
	}

	@Override
	public void jobToBeExecuted(JobExecutionContext context) {
		String jobName = context.getJobDetail().getKey().toString();
		logger.info("Job to be executed: "+jobName);
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
		String jobName = context.getJobDetail().getKey().toString();
		logger.info("Job was executed: "+jobName);
		
		if (jobException != null)
			if (jobException.getMessage() != null && !jobException.getMessage().equals("")) {
				logger.error("Job encountered exception",
						kv("Job Name", jobName),
						kv("Error", jobException));
			}
	}
}
