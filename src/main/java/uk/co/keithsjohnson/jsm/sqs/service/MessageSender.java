package uk.co.keithsjohnson.jsm.sqs.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import uk.co.keithsjohnson.jsm.sqs.configuration.JMSConfiguration;

@Component
public class MessageSender {

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String message) {
		// Send a message
		MessageCreator messageCreator = new MessageCreator() {
			// @Override
			public Message createMessage(Session session) throws JMSException {
				System.out.println("Create message: " + message);
				return session.createTextMessage(message);
			}
		};

		System.out.println("Send message.");
		jmsTemplate.send(JMSConfiguration.POSTCODE_LOCATION_FINDER_QUEUE, messageCreator);
		System.out.println("Sent message.");
	}
}
