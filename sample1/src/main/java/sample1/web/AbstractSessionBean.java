package sample1.web;

import java.util.List;

import sample1.model.Cerveza;

/**
 * esto es para controlar el flujo de la sesion del tipo que esta interactuando. 
 * si fuese un cliente javascript el se tendria que hacer cargo de lo mismoq ue se hace aca.
 * 
 * aca se van guardando las opciones de lo que va eligiendo.
 * @author julio
 *
 */
public abstract class AbstractSessionBean {

	private String growlerOrMesa; // seleccion entre growler o mesa.
	private String ubicacion; // la ubicacion que selecciona
	private List<Cerveza> seleccionCervezas; // las cervezas que selecciona
//	private String reserva; // no tengo idea >> no hay reservas solo interesa la cantidad de gentes.
	private String idSesion; // sesion del usuario // trabajos futuros > que sea trackeable.
	
	public String getGrowlerOrMesa() {
		return growlerOrMesa;
	}
	public void setGrowlerOrMesa(String growlerOrMesa) {
		this.growlerOrMesa = growlerOrMesa;
	}
	public List<Cerveza> getSeleccionCervezas() {
		return seleccionCervezas;
	}
	public void setSeleccionCervezas(List<Cerveza> seleccionCervezas) {
		this.seleccionCervezas = seleccionCervezas;
	}
	public String getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
}
