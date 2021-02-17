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
	            if(city.length()==0){
	                return "Por favor ingrese un lugar a buscar. Ejemplo : /clima?lugar=Bogota";
	            }
	            json = aw.getWeatherByCity(city);
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
