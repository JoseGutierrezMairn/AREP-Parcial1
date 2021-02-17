package edu.escuelaing.arep.weather;

import static spark.Spark.*;

import spark.Request;
import spark.Response;
import com.google.gson.Gson;


/**
 * Hello world!
 *
 */

public class SparkWebApp
{
	private static ApiWeather aw;
	
	
	public static void main(String[] args) {
		    Gson gson = new Gson();
	        port(getPort());
	        aw = new ApiWeather();
	        get("/clima", (req, res) -> {
	        	String city = req.queryParams("lugar");
	            String json = "";
	            if(city.length() > 0){
	            	System.out.println(city);
	            	json = aw.getWeatherByCity(city);
	            }else {
	            	System.out.println("Aqui papi");
	            	return "Ingrese el lugar que quiere buscar. Ejemplo : /clima?lugar=London";
	            }
	            
	            return gson.toJson(json);
	        });
	}
	
	static int getPort() {
		if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
	}

}
