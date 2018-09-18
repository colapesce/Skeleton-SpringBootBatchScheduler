package org.develop.app.batch.factory;

import static org.develop.app.batch.step.Utils.searchInJson;

import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.codehaus.jettison.json.JSONObject;
import org.develop.app.batch.dto.interfaces.Dto;
import org.develop.app.batch.exceptions.NoJobDefException;
import org.develop.app.batch.listeners.JobCompletionNotificationListener;
import org.develop.app.batch.step.Enums.PkgEnums;
import org.develop.app.batch.step.Enums.StepEnums;
import org.develop.app.batch.step.components.interfaces.ProcessorsInterface;
import org.develop.app.batch.step.components.interfaces.ReadersInterface;
import org.develop.app.batch.step.components.interfaces.WritersInterface;
import org.develop.app.persistence.entities.config.JobModel;
import org.develop.app.persistence.entities.config.StepModel;
import org.develop.app.persistence.entities.interfaces.Entita;
import org.develop.app.persistence.services.interfaces.IJobService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.JobFlowBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobFactory {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;
	
	@Autowired
	public EntityManagerFactory entityManagerFactory;

	@Autowired
	public IJobService jobService;
	
	public Job getJob(String jobName, JobCompletionNotificationListener listener) throws Exception{
		
		Job job = null;
		
		//Qui leggo le configurazioni dal DB
//		jobService.saveSample(); //Spostare su DB H2 invece che tenere questo mock
		
		JobModel jobConfig = jobService.loadByName(jobName);
		
		
		if(jobConfig != null) {
		
			List<StepModel> steps = jobConfig.getSteps();
			
			JobBuilder jobBuilder = jobBuilderFactory.get(jobConfig.getName())
					 .incrementer(new RunIdIncrementer())
					 .listener(listener);
			JobFlowBuilder jobFlowBuilder = null;
			FlowBuilder<FlowJobBuilder> flowBuilder = null;
			
			for(StepModel step : steps) {
				
				JSONObject json = new JSONObject(step.getConfig());
				
				
				if(step.getExecutionOrder() == 1) {
					jobFlowBuilder = jobBuilder.flow(getStep(step.getName(), json));
				}
				else {
					for(int i=1;i<steps.size();i++) {
					flowBuilder = jobFlowBuilder.next(getStep(step.getName(), json));
					}	
				}
			}	
			
			job = flowBuilder.end().build();
		}
		else {
			throw new NoJobDefException();
		}
		return job;
	}

	
	private Step getStep(String stepName, JSONObject stepInfo) throws Exception {
		
		ItemReader<Dto> itemReader = null;
		ItemProcessor<Dto, Entita> itemProcessor = null;
		ItemWriter<Entita> itemWriter = null;
		int chunkSize = Integer.parseInt((String)searchInJson(stepInfo, StepEnums.CHUNK));
		
		if(searchInJson(stepInfo, StepEnums.READER) != null) {
			
			JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.READER);
			
			Class<ReadersInterface<Dto>> readerClass = (Class<ReadersInterface<Dto>>)Class.forName(PkgEnums.READERS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
			ReadersInterface<Dto> reader = readerClass.newInstance();
			try {
				Method m = reader.getClass().getMethod("setDataSource", DataSource.class);
				m.invoke(reader, dataSource);
			}
			catch(Exception e) {}
			
			itemReader = reader.build(itemJson.getJSONObject("info"));
			
		}
		
		if(searchInJson(stepInfo, StepEnums.PROCESSOR) != null) {
			
			JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.PROCESSOR);
			
			Class<ProcessorsInterface<Dto,Entita>> processorClass = (Class<ProcessorsInterface<Dto, Entita>>)Class.forName(PkgEnums.PROCESSORS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
			ProcessorsInterface<Dto, Entita> processor = processorClass.newInstance();
			
			itemProcessor = processor.build(itemJson.getJSONObject("info"));
			
		}
		
		if(searchInJson(stepInfo, StepEnums.WRITER) != null) {
			
			JSONObject itemJson = (JSONObject)searchInJson(stepInfo, StepEnums.WRITER);
			
			Class<WritersInterface<Entita>> readerClass = (Class<WritersInterface<Entita>>)Class.forName(PkgEnums.WRITERS_PKG.getValue()+(String)searchInJson(itemJson, StepEnums.CLASS_NAME));
			WritersInterface<Entita> writer = readerClass.newInstance();
			
			try {
				Method m = writer.getClass().getMethod("setEntityManager", EntityManagerFactory.class);
				m.invoke(writer, entityManagerFactory);
			}
			catch(Exception e) {}
			
			itemWriter = writer.build(itemJson.getJSONObject("info"));
			
		}
		
		
		return stepBuilderFactory.get(stepName)
				.<Dto, Entita>chunk(chunkSize)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}

}
