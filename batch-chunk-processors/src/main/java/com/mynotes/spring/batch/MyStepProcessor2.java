package com.mynotes.spring.batch;

import org.springframework.batch.item.ItemProcessor;

public class MyStepProcessor2 implements ItemProcessor<XlsData, XlsData>{

	@Override
	public XlsData process(XlsData item) throws Exception {
		if(item.getAge()>25){
			return new XlsData(item.getName(),item.getAge());
		}
		return null;
	}

}
