package edu.escuelaing.arep.weather;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ApiWeather {
	private String url;
    private String key;
    private String host;

    public ApiWeather() {
        url = "http://api.openweathermap.org/data/2.5";
        key = "74c9871f37742f3c6c61c43a1434f8e2";
    }

    /**
     * Obtiene el clima de una ciudad en específico
     * @param city El nombre de la ciudad a buscar
     * @return Un string con el clima de la ciudad
     * @throws ApiConsumerException cuando la ciudad no existe , hay un problema con la conexión o la URL está mal formada
     */
    public String getWeatherByCity(String ciudad){
            URL obj;
            StringBuffer response = null;
			try {
				obj = new URL(url + "/weather?q=" + ciudad+"&appid="+key);
				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	            con.setRequestMethod("GET");
	            int responseCode = con.getResponseCode();
	            if (responseCode == HttpURLConnection.HTTP_OK) { 
	                BufferedReader in = new BufferedReader(new InputStreamReader(
	                        con.getInputStream()));
	                String inputLine;
	                response = new StringBuffer();
	                while ((inputLine = in.readLine()) != null) {
	                    response.append(inputLine);
	                }
	                in.close();
	            }
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return String.valueOf(response);
    }
}
