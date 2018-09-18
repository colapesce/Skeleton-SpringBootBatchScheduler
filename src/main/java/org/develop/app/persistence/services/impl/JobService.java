package org.develop.app.persistence.services.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.develop.app.persistence.dao.interfaces.IJobDao;
import org.develop.app.persistence.entities.config.JobModel;
import org.develop.app.persistence.entities.config.StepModel;
import org.develop.app.persistence.services.interfaces.IJobService;
import org.develop.app.persistence.utils.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService implements IJobService {

	@Autowired
	IJobDao jobDao;
	
	@Override
	@Transactional
	public JobModel saveSample() {
		
		JobModel job = new JobModel();
		job.setName("lottoJob");
		job.setInsertTs(new Timestamp((new Date().getTime())));
		
		StepModel step = new StepModel();
		step.setName("step1");
		step.setInsertTs(new Timestamp((new Date().getTime())));
		step.setExecutionOrder(1);
		step.setConfig("{\r\n" + 
				"	\"reader\":\r\n" + 
				"	{\r\n" + 
				"		\"className\":\"CSVFileReaderBuilder\",\r\n" + 
				"		\"info\":\r\n" + 
				"		{\r\n" + 
				"			\"mapperClass\":\"EstrazioneFieldSetMapper\",\r\n" + 
				"			\"resource\":\"fileRepos/inputs/storico.txt\",\r\n" + 
				"			\"tokenNames\":[\"date\",\"ruota\",\"column1\",\"column2\",\"column3\",\"column4\",\"column5\"],\r\n" + 
				"			\"delimiter\":\"\t\"\r\n" + 
				"		}\r\n" + 
				"	},\r\n" + 
				"	\"processor\":\r\n" + 
				"	{\r\n" + 
				"		\"className\":\"EstrazioneItemProcessorBuilder\",\r\n" + 
				"		\"info\":{}\r\n" + 
				"	},\r\n" + 
				"	\"writer\":\r\n" + 
				"	{\r\n" + 
				"		\"className\":\"JPAWriterBuilder\",\r\n" + 
				"		\"info\":{}\r\n" + 
				"	},\r\n" + 
				"	\"chunk\":\"500\"\r\n" + 
				"}");
		
		job.addStep(step);
		
		
		step = new StepModel();
		step.setName("step2");
		step.setInsertTs(new Timestamp((new Date().getTime())));
		step.setExecutionOrder(2);
		step.setConfig("{\r\n" + 
				"	\"reader\":\r\n" + 
				"	{  \r\n" + 
				"		\"className\":\"DBReaderBuilder\",\r\n" + 
				"		\"info\":\r\n" + 
				"		{  \r\n" + 
				"			\"mapperClass\":\"RuotaFieldSetMapper\",\r\n" + 
				"			\"query\":\"SELECT IDRUOTA ID, MIN(DATE) FIRSTUSE FROM ESTRAZIONI GROUP BY IDRUOTA\"\r\n" + 
				"		}\r\n" + 
				"	},\r\n" + 
				"	\"processor\":\r\n" + 
				"	{\r\n" + 
				"		\"className\":\"RuoteItemProcessorBuilder\",\r\n" + 
				"		\"info\":{}\r\n" + 
				"	},\r\n" + 
				"	\"writer\":\r\n" + 
				"	{\r\n" + 
				"		\"className\":\"JPAWriterBuilder\",\r\n" + 
				"		\"info\":{}\r\n" + 
				"	},\r\n" + 
				"	\"chunk\":\"10\"  \r\n" + 
				"}");
		
		job.addStep(step);
		
		jobDao.create(job);
		
		return job;
	}
	
	@Override
	public JobModel loadByName(String name) {
		
		List<QueryParameter> params = new ArrayList<>();
		params.add(new QueryParameter("name", name));
		
		return jobDao.findByParams(JobModel.class, params).get(0);
	}

	@Override
	public List<JobModel> loadAll() {
		return jobDao.findAll(JobModel.class);
	}

}
