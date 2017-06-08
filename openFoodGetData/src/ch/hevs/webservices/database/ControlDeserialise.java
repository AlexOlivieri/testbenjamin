package ch.hevs.webservices.database;

import java.util.ArrayList;
/*
 * class DataTreatment
 */
public class ControlDeserialise {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Serialisator srl = new Serialisator();
        ArrayList<Product> products = srl.deserializeFile();
        
        System.out.println("number of elements in the list: "+products.size());
	}

}
