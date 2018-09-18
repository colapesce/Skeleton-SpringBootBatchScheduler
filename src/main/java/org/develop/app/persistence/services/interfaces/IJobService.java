package org.develop.app.persistence.services.interfaces;

import java.util.List;

import org.develop.app.persistence.entities.config.JobModel;

public interface IJobService {

	public JobModel saveSample();
	public JobModel loadByName(String name);	
	public List<JobModel> loadAll();
	
}
