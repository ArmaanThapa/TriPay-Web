package com.tripayweb.test;

import org.springframework.scheduling.annotation.Scheduled;


public class CronTest {

    @Scheduled(cron = "0/10 * * * * *")
    public void testCron(){
    }
}
