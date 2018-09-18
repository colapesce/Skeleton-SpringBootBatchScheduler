package org.develop.app.batch.step.components.tasklet;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ExecuteTasklet implements Tasklet {
	private Map<Object, String> params;

	public Map<Object, String> getParams() {
		return params;
	}

	public void setParams(Map<Object, String> params) {
		this.params = params;
	}

	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Map<String, Object> jobParameters = chunkContext.getStepContext().getJobParameters();
		params.clear();
		for (Map.Entry entry : jobParameters.entrySet()) {
			params.put(entry.getKey(), entry.getValue().toString());
		}
		System.out.println("running job " + params);
		return RepeatStatus.FINISHED;
	}
}