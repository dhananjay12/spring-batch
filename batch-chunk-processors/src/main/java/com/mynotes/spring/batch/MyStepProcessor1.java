package com.mynotes.spring.batch;

import java.util.Calendar;

import org.springframework.batch.item.ItemProcessor;

public class MyStepProcessor1 implements ItemProcessor<XlsData, Customer>{

	@Override
	public Customer process(XlsData item) throws Exception {
		Customer customer=new Customer(item.getName(), item.getAge(), Calendar.getInstance());
		return customer;
	}

}
