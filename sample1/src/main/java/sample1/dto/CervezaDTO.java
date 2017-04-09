package sample1.dto;

import sample1.model.Cerveza;

public class CervezaDTO {
	
	private Cerveza cerveza;
	private Boolean cervezaElegida;
	
	public CervezaDTO(Cerveza cerveza, Boolean cervezaElegida) {
		super();
		this.cerveza = cerveza;
		this.cervezaElegida = cervezaElegida;
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
	
	

}
