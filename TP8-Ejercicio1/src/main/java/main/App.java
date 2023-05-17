package main;

import modelo.ApiClimaOnline;
import modelo.OpenWeatherServicioClima;
import modelo.WeatherHelper;

public class App {

    public static void main(String[] args) {
	// Instancio
	Long inicio, fin;
	ApiClimaOnline clima = new ApiClimaOnline(new OpenWeatherServicioClima(WeatherHelper.getProperties("weather")));

	// Pruebo

	inicio = System.currentTimeMillis();
	System.out.println(clima.temperatura());
	fin = System.currentTimeMillis();
	System.out.println((fin - inicio) / 1000f);

	System.out.println("\n***************************\n");

	inicio = System.currentTimeMillis();
	System.out.println(clima.temperatura());
	fin = System.currentTimeMillis();
	System.out.println((fin - inicio) / 1000f);

	System.out.println("\n***************************\n");

	inicio = System.currentTimeMillis();
	System.out.println(clima.temperatura());
	fin = System.currentTimeMillis();
	System.out.println((fin - inicio) / 1000f);
    }

}
