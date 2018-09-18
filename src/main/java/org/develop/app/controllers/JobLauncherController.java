package org.develop.app.controllers;

import java.util.Date;

import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.factory.JobFactory;
import org.develop.app.batch.listeners.JobCompletionNotificationListener;
import org.develop.app.batch.step.components.processors.EstrazioneItemProcessorBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/job")
public class JobLauncherController {

	private static final Logger log = LoggerFactory.getLogger(JobLauncherController.class);
	
	private static final String TIME = "time";
	private static final String JOB_NAME = "job";
	
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JobFactory jobFactory;
	@Autowired 
	JobCompletionNotificationListener jobListener;

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public void launchJob(@RequestBody String info) throws Exception {
		
		JSONObject parseJson = new JSONObject(info);
		JobParametersBuilder builder = extractParameters(parseJson);
		
		Date start = new Date();
		jobLauncher.run(jobFactory.getJob(parseJson.getString(JOB_NAME), jobListener), builder.toJobParameters());
		
		log.info("Job " + parseJson.getString(JOB_NAME) + " executed in " + ((new Date()).getTime()-start.getTime())*1000 + "sec");
	}

	private JobParametersBuilder extractParameters(JSONObject parseJson) throws Exception {
		JobParametersBuilder builder = new JobParametersBuilder();
		// org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException:
		// A job instance already exists and is complete for
		// parameters={job=executeTasklet}. If you want to run this job again, change
		// the parameters.
		builder.addLong(TIME, System.currentTimeMillis()).toJobParameters();
		builder.addString(JOB_NAME, parseJson.getString(JOB_NAME));
		return builder;
	}
}
