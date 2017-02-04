package com.mynotes.spring.batch;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowConfiguration {

	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step checkDB() {
		return stepBuilderFactory.get("checkDB").tasklet((contribution, chunkContext) -> {
			System.out.println("########## Checking DB ########");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Step checkService() {
		return stepBuilderFactory.get("checkService").tasklet((contribution, chunkContext) -> {
			System.out.println("########## Checking Service ########");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	
	@Bean
	public Step sendMail() {
		return stepBuilderFactory.get("sendMail").tasklet((contribution, chunkContext) -> {
			System.out.println("########## Sending Mail ########");
			return RepeatStatus.FINISHED;
		}).build();
	}
	
	@Bean
	public Flow initCheckFlow() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("initCheckFlow");

		flowBuilder.start(checkDB())
				.next(checkService())
				.end();

		return flowBuilder.build();
	}

	@Bean
	public Flow sendMailFLow() {
		FlowBuilder<Flow> flowBuilder = new FlowBuilder<>("sendMailFLow");

		flowBuilder.start(sendMail())
				.end();

		return flowBuilder.build();
	}
	
	

}
