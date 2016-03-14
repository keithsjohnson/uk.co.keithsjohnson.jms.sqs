package uk.co.keithsjohnson.jsm.sqs.service;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import uk.co.keithsjohnson.jsm.sqs.configuration.JMSConfiguration;

@Component
public class MessageReceiver {

	@JmsListener(destination = JMSConfiguration.POSTCODE_LOCATION_FINDER_QUEUE)
	public void processMessage(String message) {
		System.out.println("Received message: " + message);
	}
}
