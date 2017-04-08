package sample1.model;

import java.util.List;

public class LugarStock {

	private Lugar lugar;
	private Integer cantidadGente; // poca medio mucha ( banca N personas )
	private Integer aproximadoEspera; // minutos

	private List<CervezaStock> listCerveza;

	public Integer getCantidadGente() {
		return cantidadGente;
	}

	public void setCantidadGente(Integer cantidadGente) {
		this.cantidadGente = cantidadGente;
	}

	public Integer getAproximadoEspera() {
		return aproximadoEspera;
	}

	public void setAproximadoEspera(Integer aproximadoEspera) {
		this.aproximadoEspera = aproximadoEspera;
	}

	public List<CervezaStock> getListCerveza() {
		return listCerveza;
	}

	public void setListCerveza(List<CervezaStock> listCerveza) {
		this.listCerveza = listCerveza;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
}
