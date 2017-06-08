package ch.hevs.webservices.database;

import java.io.Serializable;

/*
 * Décaillet Benjamin nutrients informations
 * 11.05.2016 
 */
/*
 * class DataTreatment
 */
public class Nutrients implements Serializable{
	
	private static final long serialVersionUID = 9074355159469043953L;
	
	private String name;
	private String unit;
	private int per_hundred;
	private int per_portion;
	private int per_day;
	
	public Nutrients(String name,String unit,int per_hundred,int per_portion, int per_day){
		this.name=name;
		this.unit=unit;
		this.per_day=per_day;
		this.per_portion=per_portion;
		this.per_hundred=per_hundred;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPer_hundred() {
		return per_hundred;
	}

	public void setPer_hundred(int per_hundred) {
		this.per_hundred = per_hundred;
	}

	public int getPer_portion() {
		return per_portion;
	}

	public void setPer_portion(int per_portion) {
		this.per_portion = per_portion;
	}

	public int getPer_day() {
		return per_day;
	}

	public void setPer_day(int per_day) {
		this.per_day = per_day;
	}

}
