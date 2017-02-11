package com.mynotes.spring.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
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
		List<XlsData> dataList = new ArrayList<XlsData>();
		dataList.add(new XlsData("aaa", 24));
		dataList.add(new XlsData("bbb", 27));
		dataList.add(new XlsData("ccc", 28));
		dataList.add(new XlsData("ddd", 21));
		dataList.add(new XlsData("eee", 30));
		return new MyStepReader(dataList);
	}
	
	@Bean 
	public MyStepWriter myWriter() {		
		return new MyStepWriter();
	}
	
	@Bean
	public MyStepProcessor1 itemProcessor1() {
		return new MyStepProcessor1();
	}
	
	@Bean
	public CompositeItemProcessor<XlsData, Customer> itemProcessor() throws Exception {

		List<ItemProcessor<XlsData, ?>> delegates = new ArrayList<>(2);

		delegates.add(new MyStepProcessor2());
		delegates.add(new MyStepProcessor1());

		CompositeItemProcessor<XlsData, Customer> compositeItemProcessor =
				new CompositeItemProcessor<>();

		compositeItemProcessor.setDelegates(delegates);
		compositeItemProcessor.afterPropertiesSet();

		return compositeItemProcessor;
	}
	
	@Bean
	public Step step1() throws Exception {
		return stepBuilderFactory.get("step1")
				.<XlsData,Customer>chunk(2)
				.reader(myReader())
				.processor(itemProcessor())
				.writer(myWriter())
				.build();
	}

	/*@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<XlsData,Customer>chunk(2)
				.reader(myReader())
				.processor(itemProcessor1())
				.writer(myWriter())
				.build();
	}	*/
	
	@Bean
	public Job transitionJob() throws Exception {
		return jobBuilderFactory.get("chunkProcessor2")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}
	
	/*@Bean
	public Job transitionJob() {
		return jobBuilderFactory.get("chunkProcessor1")
				.incrementer(new RunIdIncrementer())
				.start(step1())
				.build();
	}	*/
}
