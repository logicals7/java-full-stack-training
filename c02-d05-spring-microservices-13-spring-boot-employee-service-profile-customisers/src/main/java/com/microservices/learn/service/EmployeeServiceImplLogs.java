package com.microservices.learn.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("Logging_Profile")
public class EmployeeServiceImplLogs {
    static Logger Logger = LoggerFactory.getLogger(EmployeeServiceImplLogs.class);
    static {
        Logger.info("****************************************");
        Logger.info("EmployeeServiceImplLogs class is loading...");
        Logger.info("****************************************");
    }
}
