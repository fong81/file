package com.example.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.NominalRollService;

public class NominalRollInboundJob implements Job  {
	private static final Logger logger = LoggerFactory.getLogger(NominalRollInboundJob.class);
	
	@Autowired
	private NominalRollService nominalRollService;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		nominalRollService.addNominalRoll();		
	}
}
