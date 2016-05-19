package uk.co.keithsjohnson.jsm.sqs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.annotation.EnableJms;

import uk.co.keithsjohnson.jsm.sqs.service.MessageSender;

@SpringBootApplication
@EnableJms
public class JmsSqsMainApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JmsSqsMainApplication.class, args);

		MessageSender messageSender = context.getBean(MessageSender.class);
		messageSender.send("Startup Test Message");
	}
}
