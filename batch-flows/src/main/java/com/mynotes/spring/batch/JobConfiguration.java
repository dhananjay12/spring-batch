package com.mynotes.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
			System.out.println("########## JOB STEP 1 ########");
			return RepeatStatus.FINISHED;
		}).build();
	}	
	
	@Bean
	public Job myJob(@Qualifier("initCheckFlow") Flow initCheckFlow, @Qualifier("sendMailFLow") Flow sendMailFLow) {
		
		return jobBuilderFactory.get("myJob")
				.start(initCheckFlow)
				.next(step1())
				.on("COMPLETED").to(sendMailFLow)
				.end()
				.build();
	}	

}
