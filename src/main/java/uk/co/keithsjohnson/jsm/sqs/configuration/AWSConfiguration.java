package uk.co.keithsjohnson.jsm.sqs.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

@Configuration
public class AWSConfiguration {

	@Value("${use.profile.credentials:false}")
	private boolean useProfileCredentials = false;

	@Value("${amazon.region:eu-west-1}")
	private String amazonRegion;

	@Bean
	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		if (useProfileCredentials) {
			return new ProfileCredentialsProvider();
		} else {
			return new InstanceProfileCredentialsProvider();
		}
	}

	@Bean
	public Region amazonRegion() {
		return Region.getRegion(Regions.fromName(amazonRegion));
	}
}
