package sample1.model;

public class Cerveza {

	//Estilo ALE,PALE ALE
	//Color DORADO
	//ABV 5% alc
	//País ARGENTINA
	//Volumen 710ML
	
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estilo;
	private String color;
	private String abv;
	private String pais;
	private String volumen;
	private String urlImagen;
	
	private Boolean aptoTirada;
	private Boolean aptoBotella;
	private Boolean aptoGrowler;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstilo() {
		return estilo;
	}
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAbv() {
		return abv;
	}
	public void setAbv(String abv) {
		this.abv = abv;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}
	public String getUrlImagen() {
		return urlImagen;
	}
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	public boolean isAptoBotella() {
		return aptoBotella;
	}
	public Boolean getAptoBotella() {
		return aptoBotella;
	}
	public void setAptoBotella(Boolean aptoBotella) {
		this.aptoBotella = aptoBotella;
	}
	public Boolean getAptoGrowler() {
		return aptoGrowler;
	}
	public void setAptoGrowler(Boolean aptoGrowler) {
		this.aptoGrowler = aptoGrowler;
	}
	public Boolean getAptoTirada() {
		return aptoTirada;
	}
	public void setAptoTirada(Boolean aptoTirada) {
		this.aptoTirada = aptoTirada;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
