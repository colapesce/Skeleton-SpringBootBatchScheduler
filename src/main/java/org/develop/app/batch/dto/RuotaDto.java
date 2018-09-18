package org.develop.app.batch.dto;

import java.util.Date;

import org.develop.app.batch.dto.interfaces.Dto;

public class RuotaDto implements Dto {

	private Long id;
	private Date firstUse;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getFirstUse() {
		return firstUse;
	}
	public void setFirstUse(Date firstUse) {
		this.firstUse = firstUse;
	}
	
}
