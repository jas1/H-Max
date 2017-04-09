package sample1.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import sample1.common.web.FacesUtils;
import sample1.model.Cerveza;
import sample1.model.CervezaStock;
import sample1.model.LugarStock;
import sample1.service.StockService;

@SuppressWarnings("serial")
@ViewScoped
@ManagedBean(name="pantalla4Bean")
public class Pantalla4Bean implements Serializable {

	@Inject
	StockService stockService ;
	
	private MapModel advancedModel;
	  
    private Marker marker;
    private String cantidadGente;
    private String cantidadCerveza;
    private String titulo;
    private List<LugarStock> result;
    
    @PostConstruct
    public void init() {
        advancedModel = new DefaultMapModel();
		SessionBean sb = FacesUtils.getManagedBean(SessionBean.class);
		
		sb.setUbicacion("-34.60,-58.39");
		List<Cerveza> tm = new ArrayList<>();
		tm.add(stockService.getCervezas().get(0));
		sb.setSeleccionCervezas(tm);
		
		String[] strUbi = sb.getUbicacion().split(",");
		Double radio = 5d;

        List<LugarStock> result = stockService.getLugaresByUbicacion(Double.parseDouble(strUbi[0]),Double.parseDouble(strUbi[1]), sb.getSeleccionCervezas(),radio);
        
        for (LugarStock lugarStock : result) {
        	LatLng coord1 = new LatLng(lugarStock.getLugar().getX(), lugarStock.getLugar().getY());
        	
        	Integer cantCerv = 0;
			for (CervezaStock cs : lugarStock.getListCerveza()) {
				cantCerv= cantCerv + cs.getCantidad();
			}
        	String mainTitle=lugarStock.getLugar().getNombre()+"|Gente:"+lugarStock.getCantidadGente()+"|Cerveza:"+cantCerv;
            advancedModel.addOverlay(new Marker(coord1, mainTitle, lugarStock.getLugar().getUrlStreaming(), "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
		}
        // donde estas parado:
        LatLng coord1 = new LatLng(Double.parseDouble(strUbi[0]), Double.parseDouble(strUbi[1]));
        advancedModel.addOverlay(new Marker(coord1, "Estas Aca","" , "http://maps.google.com/mapfiles/ms/micons/red-dot.png"));
        
    }
  
    public MapModel getAdvancedModel() {
        return advancedModel;
    }
      
    public void onMarkerSelect(OverlaySelectEvent event) {
    	
        marker = (Marker) event.getOverlay();
        String[] tmpSPlit = marker.getTitle().split("\\|");
        titulo = tmpSPlit[0];
        cantidadGente = tmpSPlit[1];
        cantidadCerveza = tmpSPlit[2];
    }
      
    public String getCantidadGente() {
		return cantidadGente;
	}

	public void setCantidadGente(String cantidadGente) {
		this.cantidadGente = cantidadGente;
	}

	public String getCantidadCerveza() {
		return cantidadCerveza;
	}

	public void setCantidadCerveza(String cantidadCerveza) {
		this.cantidadCerveza = cantidadCerveza;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<LugarStock> getResult() {
		return result;
	}

	public void setResult(List<LugarStock> result) {
		this.result = result;
	}

	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public Marker getMarker() {
        return marker;
    }
	
}
