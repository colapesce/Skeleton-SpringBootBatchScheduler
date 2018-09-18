package org.develop.app.persistence.entities.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@SequenceGenerator(name="CONFIG_JOB_SEQUENCE", initialValue=1, allocationSize=100)
@Entity
@Table(name="CONFIG_JOB")
public class JobModel {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONFIG_JOB_SEQUENCE")
	private long id;
	
	@NaturalId
	@Column(unique = true)
	private String name;
	private String parameters;
	private Timestamp insertTs;
	
	@OneToMany(mappedBy="job", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<StepModel> steps = new ArrayList<StepModel>();

	
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

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public List<StepModel> getSteps() {
		return steps;
	}

	public void addStep(StepModel step) {
		this.steps.add(step);
		step.setJob(this);
	}

	public Timestamp getInsertTs() {
		return insertTs;
	}

	public void setInsertTs(Timestamp insertTs) {
		this.insertTs = insertTs;
	}
	
	
	
}
