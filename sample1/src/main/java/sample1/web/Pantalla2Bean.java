package sample1.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

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
		List<Lugar> listLugares = StockMockSimulator.listadoLugares();
		listLugares.sort(HelperVarios.comparatorByGrupo());
		String grupo = "";
		List<SelectItem> selItems = new ArrayList<>();
		SelectItemGroup g = null;
		for (Lugar lugar : listLugares) {
			if (lugar.getGrupo().equalsIgnoreCase(grupo)) {
				// No hay que crear un nuevo SelectItemGroup
			} else {
				if (g != null) {
					SelectItem[] selItemsArray = new SelectItem[selItems.size()];
					g.setSelectItems(selItems.toArray(selItemsArray));
					lugares.add(g);
				}
				grupo = lugar.getGrupo();
				g = new SelectItemGroup(grupo);
				selItems = new ArrayList<>();
			}
			SelectItem tmpItem = new SelectItem(lugar.getGrupo(), lugar.getNombre());
			selItems.add(tmpItem);
		}
		SelectItem[] selItemsArray = new SelectItem[selItems.size()];
		g.setSelectItems(selItems.toArray(selItemsArray));
		lugares.add(g);
	}
	
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
