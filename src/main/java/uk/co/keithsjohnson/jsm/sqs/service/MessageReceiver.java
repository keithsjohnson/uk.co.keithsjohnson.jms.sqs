package uk.co.keithsjohnson.jsm.sqs.service;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;

@Configuration
public class MessageReceiver implements JmsListenerConfigurer {

	@Value("${aws.sqs.destination:PostcodeLocationFinderQueue}")
	private String sqsDestination;

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("myJmsEndpoint");
		endpoint.setDestination(sqsDestination);
		endpoint.setMessageListener(messageListener);
		registrar.registerEndpoint(endpoint);
	}

	MessageListener messageListener = new MessageListener() {
		@Override
		public void onMessage(Message message) {
			SQSTextMessage st = (SQSTextMessage) message;
			try {
				System.out.println("Received message: " + st.getText());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};

}