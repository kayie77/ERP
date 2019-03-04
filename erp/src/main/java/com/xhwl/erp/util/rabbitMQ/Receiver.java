package com.xhwl.erp.util.rabbitMQ;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
//public class Receiver {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
//	
//	private CountDownLatch latch = new CountDownLatch(1);
//	
//	public CountDownLatch getLatch() {
//        return latch;
//    }
//
//	public void receiveMessage(String message) {
//		LOGGER.info("Received <" + message + ">");
//		latch.countDown();
//	}
//}
