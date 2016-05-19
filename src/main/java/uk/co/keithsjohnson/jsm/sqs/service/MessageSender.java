package uk.co.keithsjohnson.jsm.sqs.service;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

	@Value("${aws.sqs.destination}")
	private String sqsDestination;

	@Autowired
	private JmsTemplate jmsTemplate;

	public void send(String message) {
		MessageCreator messageCreator = new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				String uuid = UUID.randomUUID().toString();
				System.out.println("Create message: " + message + ", JMSCorrelationID=" + uuid + ", UUID=" + uuid);
				TextMessage textMessage = session.createTextMessage(message);
				// textMessage.setJMSCorrelationID(uuid);
				textMessage.setJMSCorrelationID("1");
				textMessage.setStringProperty("JMSCorrelationID", "1");
				return textMessage;
			}
		};

		System.out.println("Send message: " + messageCreator.toString());
		jmsTemplate.send(sqsDestination, messageCreator);
		System.out.println("Sent message.");
	}
}
