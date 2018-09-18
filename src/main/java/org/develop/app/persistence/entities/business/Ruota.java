package org.develop.app.persistence.entities.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.develop.app.persistence.entities.interfaces.Entita;


@SequenceGenerator(name="RUOTE_SEQUENCE", initialValue=1, allocationSize=100)
@Entity
@Table(name="RUOTE")
public class Ruota implements Entita {

	@Id
	private long id;
	private String description;
	private String shortDescription;
	private Date firstUse;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public Date getFirstUse() {
		return firstUse;
	}
	public void setFirstUse(Date firstUse) {
		this.firstUse = firstUse;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null)
			return false;
		if(!(o instanceof Ruota))
			return false;
		
		Ruota other = (Ruota)o;
		if(this.getId() == other.getId())  
			return true;
		
		return false;
	}
	
	
}
