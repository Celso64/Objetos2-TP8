package modelo;

public class ApiClimaOnline {

    private ServicioClima servicioClima;

    public ApiClimaOnline(ServicioClima servicioClima) {
	this.servicioClima = servicioClima;
    }

    public String temperatura() {
	return this.servicioClima.getClimaActual();
    }

}
