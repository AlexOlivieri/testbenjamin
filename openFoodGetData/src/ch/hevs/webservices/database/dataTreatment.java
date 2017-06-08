package ch.hevs.webservices.database;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class dataTreatment {
	/*
	 * Benjamin Décaillet 23.05.2017
	 * Static variants to get the values of the fields of json
	 */
	private static final String data = "data";
	private static final String nameTrsl = "name_translations";
	private static final String ingrTrsl = "ingredients_translations";
	private static final String qty = "quantity"; 
	private static final String unit = "unit";
	private static final String ptQty = "portion_quantity";
	private static final String ptUnit = "portion_unit";
	private static final String nut = "nutrients";
	private static final String fr = "fr";
	private static final String perHund ="per_hundred";
	private static final String perPrt = "per_portion";
	private static final String perDay ="per_day";
	
	public static void main(String[] args) throws JSONException {

		loopOnOpenFood();
		
	}

	/*
	 * Benjamin Décaillet 23.05.2017
	 * Get all the products needed on the OpenFood website and save it to a file
	 */
	public static void loopOnOpenFood() throws JSONException{
		

        System.out.println("--------------------");
        System.out.println("BEGINNING TREATEMENT");
        System.out.println("--------------------");
		/*
		 * Benjamin Décaillet 23.05.2017
		 * Variables to stock values of jsonObjects
		 */
	    JSONObject mainJsObject;
	    JSONObject dataJsObj;
		String name;
		String ingrTrsl;
		int quantity;
		int portionQuantity;
		String unit;
		String portionUnit;
		String dataString;
		
		/*
		 * Benjamin Décaillet 23.05.2017
		 * Initialise variables for nutrients
		 */
		NutrientsTester salt;
		NutrientsTester protein;
		NutrientsTester fiber;
		NutrientsTester sugars;
		NutrientsTester carbohydrates;
		NutrientsTester stredfat;
		NutrientsTester fat;
		
		ArrayList<Product> products = new ArrayList<Product>();
		
		NutrientsTester[] nutrientsTester = new NutrientsTester[7];
		
		/*
		 * Benjamin Décaillet 23.05.2017
		 * Initialise the product variable to stock
		 */
		Product prdct;
		
        Openfood of = new Openfood();
        String jsObj = "";
        int i=0;
        int nprod=0;
        /*
         * Benjamin Décaillet 23.05.2017
         * loop to get all pages
         */
        do{
        	
        /*
         * Benjamin Décaillet 23.05.2017
         * Get openFoodPage
         */
        jsObj = of.getOpenFoodPage(i);
        //Create main Json Object from openFoodPage
        mainJsObject = new JSONObject(jsObj);
        /*
         * Benjamin Décaillet 23.05.2017
         * get information of json object after the data
         */
        dataString = mainJsObject.getJSONArray(data).toString();
        /*
         * Benjamin Décaillet 23.05.2017
         * Test if we are on the last page
         */
        if(dataString.equals("[]")){
        	break;
        }
        /*
         * Benjamin Décaillet 23.05.2017
         * Get the array of all products
         */
        JSONArray jsArr = mainJsObject.getJSONArray(data);
        int length = jsArr.length();
        for(int j=0; j<length;j++){
	        dataJsObj = new JSONObject(jsArr.getJSONObject(j).toString());
	        /*
	         * Benjamin Décaillet 23.05.2017
	         * Create instance of tester for possible available nutrients
	         */
	        salt = new NutrientsTester("salt");
			protein = new NutrientsTester("protein");
			fiber = new NutrientsTester("fiber");
			sugars = new NutrientsTester("sugars");
			carbohydrates = new NutrientsTester("carbohydrates");
			stredfat = new NutrientsTester("saturated_fat");
			fat = new NutrientsTester("fat");
			/*
			 * Benjamin Décaillet 23.05.2017
			 * Initialise empty nutrients for the current OpenfoodPage
			 */
			nutrientsTester[0]=salt;
			nutrientsTester[1]=protein;
			nutrientsTester[2]=fiber;
			nutrientsTester[3]=sugars;
			nutrientsTester[4]=carbohydrates;
			nutrientsTester[5]=stredfat;
			nutrientsTester[6]=fat;
	       /*
	        * Benjamin Décaillet 23.05.2017
	        * get desired attributes values
	        */
			//try getting name
			try{
	            name = dataJsObj.getJSONObject(nameTrsl).getString(fr);
			}
			catch (Exception e) {
	    		//System.out.println("name not available");
	    		name="No French Name";
	    	}
			//try getting ingredient
			try{
				ingrTrsl = dataJsObj.getJSONObject(dataTreatment.ingrTrsl).getString(fr);
			}
			catch (Exception e) {
	    		//System.out.println("ingredient not available");
	    		ingrTrsl="No French Ingredient";
	    	}
			//Try getting quantity
			try{
				quantity = dataJsObj.getInt(qty);
			}
			catch (Exception e) {
	    		//System.out.println("quantity not available");
	    		quantity=0;
	    	}   
			//Try getting unit
			try{
				unit = dataJsObj.getString(dataTreatment.unit);
			}
			catch (Exception e) {
	    		//System.out.println("unit not available");
	    		unit="No Unit available";
	    	}    
			//Try getting portion quantity
			try{
				portionQuantity = dataJsObj.getInt(ptQty);
			}
			catch (Exception e) {
	    		//System.out.println("Portion quantity not available");
	    		portionQuantity=0;
	    	}   
			//Try getting portion unit
			try{
				portionUnit = dataJsObj.getString(ptUnit);
			}
			catch (Exception e) {
	    		//System.out.println("Portion unit not available");
	    		portionUnit="No Portion unit available";
	    	}   
			// Test wich of the nutrients for the object are available
	        for (NutrientsTester nutrient : nutrientsTester) {
	        	try {
	        		dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId());
	        	} catch (Exception e) {
	        		nutrient.setDisp(false);
	        	}
	        }
	        /*
	         * Retrieve informations of available nutrients
	         */
	        for (NutrientsTester nutrient : nutrientsTester) {
	        	if(nutrient.getDisp()==true){
	        		//Try getting Nutrient name
	        		try{
	        			nutrient.setName(dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId()).getJSONObject(nameTrsl).getString(fr));
	        		}
	        		catch (Exception e) {
	            		//System.out.println("Nutrient Name not available");
	            		nutrient.setName("No French Name");
	            	}  
	        		//Try getting Nutrient unit
	        		try{
	        			nutrient.setUnit(dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId()).getString(dataTreatment.unit));
	        		}
	        		catch (Exception e) {
	            		//System.out.println("Nutrient unit not available");
	            		nutrient.setUnit("No unit available");
	            	} 
	        		//Try getting Nutrient per day
	        		try{
	        			nutrient.setPer_day(dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId()).getInt(perDay));
	        		}
	        		catch (Exception e) {
	            		//System.out.println("Nutrient per day not available");
	            		nutrient.setPer_day(0);
	            	} 
	        		//Try getting Nutrient per hundred
	        		try{
	        			nutrient.setPer_hundred(dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId()).getInt(perHund));
	        		}
	        		catch (Exception e) {
	            		//System.out.println("Nutrient per hundred not available");
	            		nutrient.setPer_hundred(0);
	            	} 
	        		//Try getting Nutrient per hundred
	        		try{
	        			nutrient.setPer_portion(dataJsObj.getJSONObject(nut).getJSONObject(nutrient.getId()).getInt(perPrt));
	        		}
	        		catch (Exception e) {
	            		//System.out.println("Nutrient per hundred not available");
	            		nutrient.setPer_portion(0);
	            	}
	        	}
			}
	        /*
	         * Benjamin Décaillet 23.05.2017
	         * Create the Nutrients of the products with the resulting values
	         */
	            ArrayList<Nutrients> nutr = new ArrayList<Nutrients>();
	            for (NutrientsTester nut : nutrientsTester) {
	            	if(nut.getDisp()==true){
	            	Nutrients n = new Nutrients(nut.getName(),nut.getUnit(),nut.getPer_hundred(),nut.getPer_portion(),nut.getPer_day());
	    			 nutr.add(n);
	            	}
	    		}
	            
	            Nutrients[] n = new Nutrients[nutr.size()];
	            
	            for (int x=0; x<nutr.size();x++) {
					n[x]=nutr.get(x);
				}
	            /*
	             * Benjamin Décaillet 23.05.2017
	             * Create the product and add it to the 
	             */
	            prdct = new Product(name, ingrTrsl, quantity, unit, portionQuantity, portionUnit, n);
	            products.add(prdct);
	            nprod++;
        }
        i++;
	}while(!dataString.equals("[]"));
        
        /*
         * Benjamin Décaillet 23.05.2017
         * Save the list of products to a file wich can be deserialize with the REST Application
         */
//        System.out.println("number of pages: "+i);
        Serialisator srl = new Serialisator();
        srl.serializeObject(products);
        System.out.println("--------------------");
        System.out.println("  ENDING TREATMENT  ");
        System.out.println("--------------------");
        
	}
}
