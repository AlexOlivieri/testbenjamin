package ch.hevs.webservices.database;

import java.io.Serializable;


/*
 * Décaillet Benjamin 11.05.2016
 * nutrients informations 
 */
/*
 * class DataTreatment
 */
public class NutrientsTester implements Serializable{
	
	private static final long serialVersionUID = 9074355159469043953L;
	public NutrientsTester(String id){
		this.id=id;
	}
	private String name;
	private String unit;
	private int per_hundred;
	private int per_portion;
	private int per_day;
	private String id;
	private Boolean disp=true;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getDisp() {
		return disp;
	}

	public void setDisp(Boolean disp) {
		this.disp = disp;
	}

	@Override
	public String toString() {
	return name + ", "+"unit \""+unit+"\", per hundred: "+per_hundred+
			", per portion: "+per_portion+", per day: "+per_day;
	}

}
