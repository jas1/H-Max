package sample1.model;

public class CervezaStock {

	private Cerveza cerveza;
	private Integer cantidad; 

	
//	private boolean aptoBotella;
	private Boolean hayGrowler;
	private Boolean hayTirada;
	
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Cerveza getCerveza() {
		return cerveza;
	}
	public void setCerveza(Cerveza cerveza) {
		this.cerveza = cerveza;
	}
	public Boolean getHayGrowler() {
		return hayGrowler;
	}
	public void setHayGrowler(Boolean hayGrowler) {
		this.hayGrowler = hayGrowler;
	}
	public Boolean getHayTirada() {
		return hayTirada;
	}
	public void setHayTirada(Boolean hayTirada) {
		this.hayTirada = hayTirada;
	}
}
