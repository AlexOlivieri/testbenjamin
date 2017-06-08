package ch.hevs.webservices.database;

import java.io.Serializable;
/*
 * class DataTreatment
 */
public class Product implements Serializable{
	
	private static final long serialVersionUID = 266165258568432150L;

	 private String id;
	 private String name;
	 private String ingr;
	 private int quantity;
	 private String unit;
	 private int portion_quantity;
	 private String portion_unit;
	 private Nutrients[] nutrients;
	 
	 public Nutrients[] getNutrients() {
			return nutrients;
		}
		public void setNutrients(Nutrients[] nutrients) {
			this.nutrients = nutrients;
		}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIngr() {
		return ingr;
	}
	public void setIngr(String ingr) {
		this.ingr = ingr;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getPortion_quantity() {
		return portion_quantity;
	}
	public void setPortion_quantity(int portion_quantity) {
		this.portion_quantity = portion_quantity;
	}
	public String getPortion_unit() {
		return portion_unit;
	}
	public void setPortion_unit(String portion_unit) {
		this.portion_unit = portion_unit;
	}

	 public Product(String name, String ingrTrsl, int quty, String unit, int portQuty, String portUnit, Nutrients[] nutr){
	 this.name = name;
	 this.ingr = ingrTrsl;
	 this.quantity =quty;
	 this.unit = unit;
	 this.portion_quantity = portQuty;
	 this.portion_unit = portUnit;
	 this.nutrients = nutr;
}
	public String toJson(){
			String s;
			s = "{"+
					"\"name\":\""+name+"\","+
					"\"ingr\":\""+ingr+"\","+
					"\"quantity\":"+quantity+","+
					"\"unit\":\""+unit+"\","+
					"\"portion_quantity\":"+portion_quantity+","+
					"\"portion_unit\":\""+portion_unit+"\","+
					"\"nutrients\":[";

			for (Nutrients n : nutrients) {
				s+= "{"+
						"\"name\":\""+n.getName()+"\","+
						"\"unit\":\""+n.getUnit()+"\","+
						"\"per_day\":"+n.getPer_day()+","+
						"\"per_hundred\":"+n.getPer_hundred()+","+
						"\"per_portion\":"+n.getPer_portion()+""+
					"},";
			}
			s = s.substring(0, s.length()-1);
			s+="]}";
			return s;
		}
	
}
