package com.mynotes.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	@StepScope
	public Tasklet myTasklet(@Value("#{jobParameters['param']}") String param) {
		return (stepContribution, chunkContext) -> {
			System.out.println("################"+param);
			System.out.println("################ MyJob - STEP 1");
			return RepeatStatus.FINISHED;
		};
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.tasklet(myTasklet(null))
				.build();
	}
	
	@Bean
	public Job helloWorldJob() {
		return jobBuilderFactory.get("MyJob")
				.start(step1())
				.build();
	}

}
