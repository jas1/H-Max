package sample1.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sample1.helpers.StockMockSimulator;
import sample1.model.Cerveza;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name="pantalla3BarBean")
public class Pantalla3BarBean implements Serializable {

	private List<Cerveza> cervezas;
	
	@PostConstruct
	private void init(){
		cervezas = StockMockSimulator.listadoCervezas();
	}

	public List<Cerveza> getCervezas() {
		return cervezas;
	}

	public void setCervezas(List<Cerveza> cervezas) {
		this.cervezas = cervezas;
	}
			
}
