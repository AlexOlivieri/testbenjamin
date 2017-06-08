package ch.hevs.webservices.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * class DataTreatment
 */
public class Serialisator implements Serializable {

	private static final long serialVersionUID = 1L;
//	public static final String lgFile = "D:/Hes/HES 2eme ann�e/S2/624-2 G�nie logiciel/Products.txt";
	public static final String lgFile = "File/Products.txt";
	/*
	 * 19.05.2017
	 * Save file of products to location define in lgFile
	 */
	public static void serializeObject(ArrayList<Product> prdctArray){

		try{		    
		    FileOutputStream fichier = new FileOutputStream(lgFile);
			ObjectOutputStream oos = new ObjectOutputStream(fichier);
			
			//�crit un objet dans la r�f�rence que l'on a d�fini avec les outputStream
			oos.writeObject(prdctArray);
			oos.flush();
			oos.close();
			}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Product> deserializeFile(){
		try {
			
			//D�fini l'emplacement o� le fichier est
			FileInputStream fichier = new FileInputStream(lgFile);
			ObjectInputStream ois = new ObjectInputStream(fichier);
			
			//stock dans une variable l'objet en le castant du bon type
			ArrayList<Product> products = (ArrayList<Product>) ois.readObject();
			//Ferme le stream cr�� pour le fichier
			ois.close();
			//actions to do with the file stocked in ArrayList
			
			return products;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
