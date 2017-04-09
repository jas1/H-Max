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
	public List<LugarStock> getLugaresGrowlerByUbicacion(Double x, Double y, List<Cerveza> tipoCervezasParsed) {
		
		Double radio = 5d; // 5 km , TODO: ver de si lo paso por parametro o no.
		// dame los lugares cerca de x/y
		List<Lugar> lugaresXY = emMock.getLugaresXY(x, y, radio);

		// de esos , cuales tienen cerveza de la lista.
		List<LugarStock> ret = emMock.consultarStockCervezaYgenteParaLugares(lugaresXY);
		
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
	public List<LugarStock> getLugaresByUbicacion(Double x, Double y, List<Cerveza> tipoCervezasParsed) {
		
		Double radio = 5d; // 1 km , TODO: ver de si lo paso por parametro o no.
		// dame los lugares cerca de x/y
		List<Lugar> lugaresXY = emMock.getLugaresXY(x, y, radio);

		// de esos , cuales tienen cerveza de la lista.
		List<LugarStock> ret = emMock.consultarStockCervezaYgenteParaLugares(lugaresXY);
		
		return ret;
	}

	// que bares tienen las cervezas para la ubicacion en el momento.

}
