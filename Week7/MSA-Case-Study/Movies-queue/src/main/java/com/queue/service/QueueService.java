package com.queue.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@Service("queue")
public class QueueService {

	private AWSCredentials cred;
	private AmazonSQS sqs;
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;
	private String url = "https://sqs.us-east-1.amazonaws.com/967240801169/2009Queue";
	

	@PostConstruct
	public void BuildQueue() {
		cred = new BasicAWSCredentials(accessKey, secretKey);
		sqs = AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(cred))
				.withRegion(Regions.US_EAST_1).build();
	}
	
	public void sendMessage(String message) {
		SendMessageRequest send = new SendMessageRequest().withQueueUrl(url)
				.withMessageBody("this is our message: "+message);
		sqs.sendMessage(send);
	}
	
	
	public String getMessage() {
		return sqs.receiveMessage(url).getMessages().get(0).getBody();
	}
}
