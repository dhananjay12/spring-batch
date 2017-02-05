package com.mynotes.spring.batch;

import java.util.Iterator;
import java.util.List;
import org.springframework.batch.item.ItemReader;

public class MyStepReader implements ItemReader<String> {

	private final Iterator<String> dataList;

	public MyStepReader(List<String> dataList) {
		this.dataList = dataList.iterator();
	}

	@Override
	public String read() throws Exception {
		if(this.dataList.hasNext()) {
			return this.dataList.next();
		}
		else {
			return null;
		}
	}
}
