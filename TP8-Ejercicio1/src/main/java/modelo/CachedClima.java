package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class CachedClima implements ServicioClima {

    private final Integer minutosDeCache;
    private ServicioClima servicio;

    private String ultimoClima;
    private LocalDateTime ultimaConsulta;

    public CachedClima(ServicioClima servicio, Integer minutosDeCache) {
	this.servicio = servicio;
	this.minutosDeCache = minutosDeCache;
    }

    @Override
    public String getClimaActual() {

	if (Objects.isNull(this.ultimoClima) || this.seVencioLaCache())
	    this.consultar();

	return this.ultimoClima;
    }

    private void consultar() {
	this.ultimoClima = this.servicio.getClimaActual();
	this.ultimaConsulta = LocalDateTime.now();
    }

    private Boolean seVencioLaCache() {
	LocalDateTime ahora = LocalDateTime.now();
	LocalDateTime vencimientoDeCache = this.ultimaConsulta.plusMinutes(this.minutosDeCache);

	return ahora.isAfter(vencimientoDeCache);
    }

}
