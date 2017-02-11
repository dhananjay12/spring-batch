package com.mynotes.spring.batch;

import java.util.Iterator;
import java.util.List;
import org.springframework.batch.item.ItemReader;

public class MyStepReader implements ItemReader<XlsData> {

	private final Iterator<XlsData> dataList;

	public MyStepReader(List<XlsData> dataList) {
		this.dataList = dataList.iterator();
	}

	@Override
	public XlsData read() throws Exception {
		if(this.dataList.hasNext()) {
			return this.dataList.next();
		}
		else {
			return null;
		}
	}
}
