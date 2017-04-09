package sample1.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import sample1.common.web.FacesUtils;
import sample1.dto.CervezaDTO;
import sample1.helpers.HelperVarios;
import sample1.model.BarGrow;
import sample1.model.Cerveza;
import sample1.service.StockService;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name="pantalla3BarBean")
public class Pantalla3BarBean implements Serializable {

	private List<CervezaDTO> cervezas;
	private Cerveza cervezaSeleccionada;
	
	@Inject
	StockService stockService;
	
	SessionBean sb;
	
	@PostConstruct
	private void init(){
		SessionBean sb = FacesUtils.getManagedBean(SessionBean.class);
    	BarGrow selected = BarGrow.getBarGrow(sb.getGrowlerOrMesa());

		List<Cerveza> tmpCervezas = stockService.getCervezasDisponibilidad(selected);
		cervezas = new ArrayList<CervezaDTO>();
		for (Cerveza cerveza : tmpCervezas) {
			cervezas.add(new CervezaDTO(cerveza, false));
		}
	}

	public void accionContinuar(){
		HelperVarios.getCervezasSeleccionadas(cervezas);
	}

	public Cerveza getCervezaSeleccionada() {
		return cervezaSeleccionada;
	}

	public void setCervezaSeleccionada(Cerveza cervezaSeleccionada) {
		this.cervezaSeleccionada = cervezaSeleccionada;
	}

	public List<CervezaDTO> getCervezas() {
		return cervezas;
	}

	public void setCervezas(List<CervezaDTO> cervezas) {
		this.cervezas = cervezas;
	}

			
}
