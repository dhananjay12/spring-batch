package com.mynotes.spring.batch;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

public class MyStepWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("Writer chunk size: " + items.size());

		for (String item : items) {
			System.out.println("Writer::::::" + item);
		}
	}
}