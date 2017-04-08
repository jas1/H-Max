package sample1.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import sample1.helpers.HelperVarios;
import sample1.model.Cerveza;
import sample1.model.Lugar;
import sample1.model.LugarStock;


public class EntityManagerMock {

	/* esto esta static para mockear, 
	 * sino aca ira a la DB a buscar la data
	 * */
	private static List<Cerveza> cervezasTipo = new ArrayList<>();
	private static List<Lugar> lugares = new ArrayList<>();
	private static List<LugarStock> lugaresStock = new ArrayList<>();
	
	public List<Cerveza> getCervezasTipo(){
		return cervezasTipo;
	}
	
	public List<Lugar> getLugares(){
		return lugares;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param radio expresado en km ? FIXME
	 * @return
	 */
	public List<Lugar> getLugaresXY(Integer x, Integer y , Integer radio){
		return lugares.stream().filter(lugar -> HelperVarios.filtraDistancia(lugar,x,y,radio)).collect(Collectors.toList());
	}
	
	
	
}
