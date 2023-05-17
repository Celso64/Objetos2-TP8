package modelo;

import java.util.Properties;
import java.util.ResourceBundle;

public abstract class WeatherHelper {

    public static Properties getProperties(String nombreArchivo) {

	ResourceBundle bundle = ResourceBundle.getBundle(nombreArchivo);

	Properties prop = new Properties();

	prop.put("apiKey", bundle.getObject("api_key"));
	prop.put("ciudad", bundle.getObject("ciudad"));
	prop.put("codigo", bundle.getObject("codigo"));

	return prop;
    }

}
