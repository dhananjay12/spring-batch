package com.mynotes.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobLaunchController {
	
	@Autowired
	private JobLauncher jobLauncher;

	
	@Autowired
	private Job job;

	@RequestMapping(value = "/launch", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void launch(@RequestParam("param") String param) throws Exception {
		JobParameters jobParameters =
				new JobParametersBuilder()
					.addString("param", param)
					.toJobParameters();
		
		this.jobLauncher.run(job, jobParameters);
	
		
	}

}
