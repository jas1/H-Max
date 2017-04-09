package sample1.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import sample1.common.web.FacesUtils;
import sample1.helpers.HelperVarios;
import sample1.helpers.StockMockSimulator;
import sample1.model.Lugar;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name="pantalla2Bean")
public class Pantalla2Bean implements Serializable {

	private List<SelectItem> lugares;
	private String ubicacion;
	
	@PostConstruct
	private void init() {
		lugares = new ArrayList<>();
		List<Lugar> listLugares = StockMockSimulator.listadoLugares();
		listLugares.sort(HelperVarios.comparatorByGrupo());
		String grupo = "";
		SelectItem tmpItem = null;
		for (Lugar lugar : listLugares) {
			if (lugar.getGrupo().equalsIgnoreCase(grupo)) {
				// No hay que crear un nuevo item
			} else {
				if (grupo != "" && !grupo.contains("movil")) {
					tmpItem = new SelectItem(grupo, grupo);
					lugares.add(tmpItem);
				}
				grupo = lugar.getGrupo();
			}
		}
	}
	
	public String accionContinuar(){
		if (ubicacion != null) {
			SessionBean sb = FacesUtils.getManagedBean(SessionBean.class);
			sb.setUbicacion(ubicacion);
			if (sb.getGrowlerOrMesa().equalsIgnoreCase("growler")) {
				return "pantalla3growler";
			} else if (sb.getGrowlerOrMesa().equalsIgnoreCase("bar")) {
				return "pantalla3bar";
			} else {
				//Entraron hasta acá sin el paso previo, así que no navego
				return null;
			}
			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
			          new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe selecccionar una ubicación"));
			return null;
		}		
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
