package com.example.config;

import javax.annotation.PostConstruct;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.example.job.QuartzJobListener;

@Component
public class QuartzJobListenerConfig {
	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @PostConstruct
    public void addListeners() throws SchedulerException {
        schedulerFactoryBean.getScheduler().getListenerManager().addJobListener(new QuartzJobListener());
    }
}
