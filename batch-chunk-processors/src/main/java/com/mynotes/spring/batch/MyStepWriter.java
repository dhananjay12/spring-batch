package com.mynotes.spring.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class MyStepWriter implements ItemWriter<Customer> {

	@Override
	public void write(List<? extends Customer> items) throws Exception {
		System.out.println("Writer chunk size: " + items.size());

		for (Customer item : items) {
			System.out.println("Writer::::::" + item);
		}
	}
}