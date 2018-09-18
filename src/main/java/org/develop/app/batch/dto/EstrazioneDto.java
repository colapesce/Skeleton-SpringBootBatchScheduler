package org.develop.app.batch.dto;

import java.util.Calendar;

import org.develop.app.batch.dto.interfaces.Dto;

public class EstrazioneDto implements Dto {

private Long id;
	
	private String ruota;
	private Calendar date;
	private int column1;
	private int column2;
	private int column3;
	private int column4;
	private int column5;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRuota() {
		return ruota;
	}
	public void setRuota(String ruota) {
		this.ruota = ruota;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public int getColumn1() {
		return column1;
	}
	public void setColumn1(int column1) {
		this.column1 = column1;
	}
	public int getColumn2() {
		return column2;
	}
	public void setColumn2(int column2) {
		this.column2 = column2;
	}
	public int getColumn3() {
		return column3;
	}
	public void setColumn3(int column3) {
		this.column3 = column3;
	}
	public int getColumn4() {
		return column4;
	}
	public void setColumn4(int column4) {
		this.column4 = column4;
	}
	public int getColumn5() {
		return column5;
	}
	public void setColumn5(int column5) {
		this.column5 = column5;
	}

	@Override
	public String toString() {
		
		return this.getDate().getTime() + " " +
			   this.getRuota() + " " +
			   this.getColumn1() + " " +
			   this.getColumn2() + " " +
			   this.getColumn3() + " " +
			   this.getColumn4() + " " +
			   this.getColumn5();
	}
	
}
