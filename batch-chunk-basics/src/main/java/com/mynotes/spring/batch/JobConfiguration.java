package com.mynotes.spring.batch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public MyStepReader myReader() {
		List<String> dataList = Arrays.asList("aaa","bbb","ccc","ddd","eee","fff");		
		return new MyStepReader(dataList);
	}
	
	@Bean
	public MyStepWriter myWriter() {		
		return new MyStepWriter();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<String,String>chunk(3)
				.reader(myReader())
				.writer(myWriter())
				.build();
	}	
	
	@Bean
	public Job transitionJob() {
		return jobBuilderFactory.get("chunkBasic1")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}	
}
