package org.develop.app.persistence.entities.config;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SequenceGenerator(name="CONFIG_STEP_SEQUENCE", initialValue=1, allocationSize=100)
@Entity
@Table(name="CONFIG_STEP")
public class StepModel {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONFIG_STEP_SEQUENCE")
	private long id;
	private String name;
	
	@Column(length=4000)
	private String config;
	private Timestamp insertTs;
	private int executionOrder;
	
	@ManyToOne
	private JobModel job;

	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public Timestamp getInsertTs() {
		return insertTs;
	}

	public void setInsertTs(Timestamp insertTs) {
		this.insertTs = insertTs;
	}

	public int getExecutionOrder() {
		return executionOrder;
	}

	public void setExecutionOrder(int executionOrder) {
		this.executionOrder = executionOrder;
	}

	public JobModel getJob() {
		return job;
	}

	public void setJob(JobModel job) {
		this.job = job;
	}
	
}
