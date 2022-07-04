package com.example.config;

import static net.logstash.logback.argument.StructuredArguments.kv;

import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.EverythingMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import com.example.job.NominalRollInboundJob;
import com.example.job.QuartzJobListener;

/**
 * QuartzScheduler to schedule jobs to run on start up
 */
@Configuration
public class QuartzScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzScheduler.class);
	
	 @Autowired
	 private QuartzConfig quartzConfig;
	
	@PostConstruct
	public void scheduleNominalRollIncomingJob() {
		try {
			Scheduler scheduler = quartzConfig.schedulerFactoryBean().getScheduler();

			JobDetail jobDetail = JobBuilder.newJob(NominalRollInboundJob.class)
					.withIdentity("NominalRollInboundJob", "Group 1")
					.build();

			//Trigger
			Trigger trigger = TriggerBuilder.newTrigger()
					.withIdentity("NominalRollInboundTrigger", "In intervals of 20s")
					.startAt(new Date(System.currentTimeMillis() + 10000L))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(20)
							.repeatForever())
					.build();

			scheduler.scheduleJob(jobDetail, trigger);
			scheduler.getListenerManager().addJobListener(new QuartzJobListener(), EverythingMatcher.allJobs());
			scheduler.start();
			
			
		} catch (SchedulerException | IOException e) {
			logger.error("QuartzScheduler Error",
					kv("error", e.getMessage()));
		}
	}
}