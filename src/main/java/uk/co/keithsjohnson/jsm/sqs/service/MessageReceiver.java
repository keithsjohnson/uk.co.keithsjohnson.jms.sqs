package uk.co.keithsjohnson.jsm.sqs.service;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.stereotype.Component;

import com.amazon.sqs.javamessaging.message.SQSTextMessage;

@Component
public class MessageReceiver implements JmsListenerConfigurer {

	@Value("${aws.sqs.destination}")
	private String sqsDestination;

	@Override
	public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
		SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
		endpoint.setId("myJmsEndpoint");
		endpoint.setDestination(sqsDestination);
		endpoint.setMessageListener(messageListener);
		// endpoint.setSelector("JMSCorrelationID = '1'");
		registrar.registerEndpoint(endpoint);
	}

	MessageListener messageListener = new MessageListener() {
		@Override
		public void onMessage(Message message) {
			SQSTextMessage st = (SQSTextMessage) message;
			try {
				System.out.println(
						"Received message: " + st.getText() + ", JMSCorrelationID=" + st.getJMSCorrelationID()
								+ ", UUID=" + st.getStringProperty("UUID"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	};

}