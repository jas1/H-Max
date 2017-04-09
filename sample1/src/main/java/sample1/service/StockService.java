package sample1.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import sample1.dao.EntityManagerMock;
import sample1.model.BarGrow;
import sample1.model.Cerveza;
import sample1.model.Lugar;
import sample1.model.LugarStock;

/**
 * la idea de esta clase es devolver los stock asociados.
 * 
 * @author julio
 *
 */
@Stateless
public class StockService {

	@Inject
	private EntityManagerMock emMock;

	/**
	 * devuelve los tipos de cervezas existentes
	 * 
	 * @return
	 */
	public List<Cerveza> getCervezas() {
		return emMock.getCervezasTipo();
	}

//	public List<Cerveza> getCervezasDisponibilidadEnZona(BarGrow selected,Double x, Double y,Double radio) {
//		
////		tengo el lugar
//		List<Lugar> lugaresCercanos = emMock.getLugaresXY(x, y, radio);
//		
//		List<Lugar> lugaresCercanosFilterSeleccion;
//		
//		
//		switch (selected) {
//			case GROW:
//				lugaresCercanosFilterSeleccion= lugaresCercanos.stream().filter(lugar -> lugar.getTieneGrowler()).collect(Collectors.toList());
//			default:
//				lugaresCercanosFilterSeleccion= lugaresCercanos;
//		}
//		
//		//	hacer el stcok de lugares
////		lugaresCercanosFilterSeleccion
//		List<LugarStock> tmp = emMock.getStockCervezaYgenteParaLugares(lugaresCercanosFilterSeleccion);
//		
//		Set<CervezaStock> cerTmp = new HashSet<>(); 
//		// que haya cerveza, y despues la incluyo.
//		tmp.stream().filter(cs->cs.getCantidad() > 0).forEach(ls->cerTmp.addAll(ls.getListCerveza()));
//		
//		cerTmp.stream().for(cerT->cerT.getCerveza()).
//		
//	}.
	
	public List<Cerveza> getCervezasDisponibilidad(BarGrow selected) {
		switch (selected) {
		case BAR:
			return emMock.getCervezasTipo().stream().filter(cer -> cer.getAptoTirada()).collect(Collectors.toList());
		case GROW:
			return emMock.getCervezasTipo().stream().filter(cer -> cer.getAptoGrowler()).collect(Collectors.toList());

		default:
			throw new RuntimeException("Seleccion no valida");
		}
	}

	/**
	 * para devolver los lugares que estan disponibles, todos
	 * 
	 * @return
	 */
	public List<Lugar> getLugares() {
		return emMock.getLugares();
	}

	/**
	 * por default devuelve con un radio de 5 KM
	 * @param x
	 * @param y
	 * @param tipoCervezasParsed
	 * @return
	 */
	// que bares tienen growler para la ubicacion: growl + lugar a partir del:
	// ubicacion.
	public List<LugarStock> getLugaresGrowlerByUbicacion(Double x, Double y, List<Cerveza> tipoCervezasParsed,Double radio) {
		

		// dame los lugares cerca de x/y
		List<Lugar> lugaresXY = emMock.getLugaresXY(x, y, radio);

		// de esos , cuales tienen cerveza de la lista.
		List<LugarStock> ret = emMock.getStockCervezaYgenteParaLugares(lugaresXY);
		
		return ret.stream().filter(ls-> ls.getLugar().getTieneGrowler()).collect(Collectors.toList());
	}
	

	/**
	 * por default devuelve con un radio de 5 KM
	 * @param x
	 * @param y
	 * @param tipoCervezasParsed
	 * @return
	 */
	// que bares tienen growler para la ubicacion: growl + lugar a partir del:
	// ubicacion.
	public List<LugarStock> getLugaresByUbicacion(Double x, Double y, List<Cerveza> tipoCervezasParsed,Double radio) {
		
		// dame los lugares cerca de x/y
		List<Lugar> lugaresXY = emMock.getLugaresXY(x, y, radio);

		// de esos , cuales tienen cerveza de la lista.
		List<LugarStock> ret = emMock.getStockCervezaYgenteParaLugares(lugaresXY);
		
		return ret;
	}

	// que bares tienen las cervezas para la ubicacion en el momento.

}
