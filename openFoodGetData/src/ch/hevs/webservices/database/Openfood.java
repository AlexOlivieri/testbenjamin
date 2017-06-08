package ch.hevs.webservices.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
 * Décaillet Benjamin 11.05.2016
 *  Get openFood pages
 */
/*
 * Project DataTreatment
 */
public class Openfood {
	public String getOpenFoodPage(int page)
    {
        try {
            URL url = new URL("https://www.openfood.ch/api/v3/products?excludes=images%2Ccreated_at%2Cupdated_at%2Cdisplay_name_translations%2Corigin_translations%2Cstatus%2Calcohol_by_volume&page%5Bsize%5D=200&page%5Bnumber%5D="+page);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty ("Authorization", "Token token=\"730030b90b8a5e90e8380f27a4966e70\"");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            String response="";
            while ((output = br.readLine()) != null) {
                response += output;
            }
            conn.disconnect();

            return response;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
