package uk.co.keithsjohnson.jsm.sqs.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import uk.co.keithsjohnson.jsm.sqs.service.MessageSender;

@RestController
public class SendMessageAction {

	@Autowired
	private MessageSender messageSender;

	@RequestMapping(value = "/send")
	public @ResponseBody String locatePostcode(String message) {
		messageSender.send(message);
		return "Sent Message: " + message;
	}
}
