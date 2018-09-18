package org.develop.app.persistence.entities.business;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.develop.app.persistence.entities.interfaces.Entita;

@SequenceGenerator(name="ESTRAZIONI_SEQUENCE", initialValue=1, allocationSize=100)
@Entity
@Table(name="ESTRAZIONI")
public class Estrazione implements Entita{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTRAZIONI_SEQUENCE")
	private long id;
	
	private long idRuota;
	private Calendar date;
	private int column1;
	private int column2;
	private int column3;
	private int column4;
	private int column5;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getIdRuota() {
		return idRuota;
	}
	public void setIdRuota(long idRuota) {
		this.idRuota = idRuota;
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
	
}
