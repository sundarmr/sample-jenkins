package com.training.kafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
	/*	from("timer:test?period=1&repeatCount=100")
			.onException(Exception.class)
				.handled(true)
				.log("${exception}")
				.end()
			.setBody().constant("Sundar", String.class)
			.to("kafka:topic1?brokers=my-route-kafka.apps-crc.testing:443&sslTruststoreLocation=/Users/smunirat/code/openshift/client.truststore.jks&sslTruststorePassword=password");

		*/
		onException(Exception.class)
			.handled(true)
			.to("log:test?showAll=true&multiline=true");
		
		from("kafka:topic1?brokers=kafka-cluster-kafka-external-bootstrap-kafka.apps-crc.testing:443&sslTruststoreLocation=/Users/smunirat/code/openshift/client.truststore.jks&sslTruststorePassword=password&breakOnFirstError=true&groupId=sundar&seekTo=BEGINNING")
		.onException(Exception.class)
		.handled(true)
		.log("Exception is \n${exception}")
		.end()	
		.log("\n\n\n${body}\n\n\n");
		
	}
	

}
