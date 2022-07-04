package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NominalRollService {
	private static final Logger logger = LoggerFactory.getLogger(NominalRollService.class);
	
	public void addNominalRoll() {
		logger.info("Inside nominal roll service");
	}
}
