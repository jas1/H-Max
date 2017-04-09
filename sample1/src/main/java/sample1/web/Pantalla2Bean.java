package sample1.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import sample1.common.web.FacesUtils;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name="pantalla2Bean")
public class Pantalla2Bean implements Serializable {

	private List<SelectItem> lugares;
	private String ubicacion;
	
	public String accionContinuar(){
		FacesUtils.getManagedBean(SessionBean.class).setUbicacion(ubicacion);
		return "pantalla3";
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public List<SelectItem> getLugares() {
		return lugares;
	}

	public void setLugares(List<SelectItem> lugares) {
		this.lugares = lugares;
	}	
			
}
