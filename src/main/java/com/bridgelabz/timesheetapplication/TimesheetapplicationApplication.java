package com.bridgelabz.timesheetapplication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class TimesheetapplicationApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(TimesheetapplicationApplication.class,args);
		log.info("============================================================================================");
		log.info(" ----- :: Time Sheet Application Running Successfully... :: -----");
		log.info("MySQL Database UserName is {} database....", applicationContext.getEnvironment().getProperty("spring.datasource.username"));
		log.info("============================================================================================");
		}
	}

