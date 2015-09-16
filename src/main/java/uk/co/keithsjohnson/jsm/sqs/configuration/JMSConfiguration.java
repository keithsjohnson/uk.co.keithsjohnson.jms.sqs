package uk.co.keithsjohnson.jsm.sqs.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

import org.skyscreamer.nevado.jms.NevadoConnectionFactory;
import org.skyscreamer.nevado.jms.connector.amazonaws.AmazonAwsSQSConnectorFactory;
import org.skyscreamer.nevado.jms.destination.NevadoQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;

import com.amazonaws.auth.AWSCredentialsProvider;

import uk.co.keithsjohnson.jsm.sqs.service.MessageReceiver;

@Configuration
public class JMSConfiguration {

	public static final String POSTCODE_LOCATION_FINDER_QUEUE = "PostcodeLocationFinderQueue";

	@Autowired
	private MessageReceiver messageReceiver;

	@Autowired
	private AWSCredentialsProvider awsCredentialsProvider;

	@Bean
	public AmazonAwsSQSConnectorFactory sqsConnectionFactory() {
		return new AmazonAwsSQSConnectorFactory();
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		NevadoConnectionFactory nevadoConnectionFactory = new NevadoConnectionFactory();
		nevadoConnectionFactory.setSqsConnectorFactory(sqsConnectionFactory());
		nevadoConnectionFactory.setAwsCredentials(awsCredentialsProvider.getCredentials());
		nevadoConnectionFactory.setAwsSQSEndpoint("http://sqs.eu-west-1.amazonaws.com");
		return nevadoConnectionFactory;
	}

	@Bean
	public Queue queue() {
		Queue queue = new NevadoQueue(POSTCODE_LOCATION_FINDER_QUEUE);
		return queue;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setDefaultDestinationName(POSTCODE_LOCATION_FINDER_QUEUE);
		jmsTemplate.setConnectionFactory(connectionFactory());
		return jmsTemplate;
	}

	@Bean
	public MessageListenerAdapter messageListener() {
		MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter();
		messageListenerAdapter.setDelegate(messageReceiver);
		messageListenerAdapter.setDefaultListenerMethod("processMessage");
		messageListenerAdapter.setDefaultResponseDestination(queue());
		return messageListenerAdapter;
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer() {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
		simpleMessageListenerContainer.setMessageListener(messageListener());
		simpleMessageListenerContainer.setDestination(queue());

		return simpleMessageListenerContainer;
	}

}
