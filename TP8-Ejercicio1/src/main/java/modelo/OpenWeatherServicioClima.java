package modelo;

import java.io.IOException;
import java.util.Properties;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpenWeatherServicioClima implements ServicioClima {

    private static final String[] URL = { "https://api.openweathermap.org/data/2.5/weather?q=", ",",
	    "&lang=sp&APPID=" };

    private final String API_KEY;
    private final String CIUDAD;
    private final String CODIGO;

    private final OkHttpClient conexion = new OkHttpClient();
    private final Gson gson = new Gson();

    public OpenWeatherServicioClima(Properties propiedades) {
	this.API_KEY = propiedades.getProperty("apiKey");
	this.CIUDAD = propiedades.getProperty("ciudad");
	this.CODIGO = propiedades.getProperty("codigo");
    }

    @Override
    public String getClimaActual() {

	Clima clima = null;

	StringBuilder peticion = new StringBuilder(128);

	peticion.append(URL[0]).append(CIUDAD);
	peticion.append(URL[1]).append(CODIGO);
	peticion.append(URL[2]).append(API_KEY);

	Request request = new Request.Builder().url(peticion.toString()).build();

	try (Response response = this.conexion.newCall(request).execute()) {
	    String cuerpo = response.body().string();

	    clima = this.gson.fromJson(cuerpo, Clima.class);

	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

	return clima.toString();
    }

}
