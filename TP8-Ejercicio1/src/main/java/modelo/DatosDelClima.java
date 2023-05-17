package modelo;

class DatosDelClima {

    private Double temp;

    public DatosDelClima(Double temp, Double feels_like, Double temp_min, Double temp_max, Double pressure,
	    Double humidity) {
	this.temp = temp;

    }

    @Override
    public String toString() {
	return "temp: " + temp;
    }

    public Double getTemp() {
	return temp;
    }

}
