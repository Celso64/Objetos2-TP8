package modelo;

public class Clima {

    private DatosDelClima main;

    public Clima(DatosDelClima main) {
	this.main = main;
    }

    @Override
    public String toString() {
	return main.toString();
    }

    public Double getTemperatura() {
	return this.main.getTemp();
    }

}
