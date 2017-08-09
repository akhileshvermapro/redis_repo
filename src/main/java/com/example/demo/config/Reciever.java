package com.example.demo.config;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;

public class Reciever {
	
	public CountDownLatch latch;
	
	@Autowired
	public Reciever(CountDownLatch latch){
		this.latch = latch;
	}
	
	 public void receiveMessage(String message) {
	        latch.countDown();
	 }

}
