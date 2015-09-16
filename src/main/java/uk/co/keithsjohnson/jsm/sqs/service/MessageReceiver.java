package uk.co.keithsjohnson.jsm.sqs.service;

import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	public void processMessage(String message) {
		System.out.println("Received message: " + message);
	}
}
