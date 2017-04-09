package sample1.dto;

import sample1.model.Cerveza;

public class CervezaDTO {
	
	private Cerveza cerveza;
	private Boolean cervezaElegida;
	private int cantGrowlers;
	
	public CervezaDTO(Cerveza cerveza) {
		super();
		this.cerveza = cerveza;
		this.cervezaElegida = false;
		this.cantGrowlers = 0;
		
	}
	public Cerveza getCerveza() {
		return cerveza;
	}
	public void setCerveza(Cerveza cerveza) {
		this.cerveza = cerveza;
	}
	public Boolean getCervezaElegida() {
		return cervezaElegida;
	}
	public void setCervezaElegida(Boolean cervezaElegida) {
		this.cervezaElegida = cervezaElegida;
	}
	public int getCantGrowlers() {
		return cantGrowlers;
	}
	public void setCantGrowlers(int cantGrowlers) {
		this.cantGrowlers = cantGrowlers;
	}
	
	

}
