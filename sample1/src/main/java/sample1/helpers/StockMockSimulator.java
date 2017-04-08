package sample1.helpers;

import java.util.ArrayList;
import java.util.List;

import sample1.model.Lugar;
import sample1.model.LugarStock;

public class StockMockSimulator {

	
	public static List<LugarStock> generarLugarStockPara(List<Lugar> lugares){
		
		List<LugarStock> lugarStock = new ArrayList<>();
		
		for (Lugar lugar : lugares) {
			LugarStock tmpStock = new LugarStock();
			
			
			lugarStock.add(tmpStock);
		}
		
		return lugarStock;
	}
	
	public static List<Lugar> listadoLugares(){
		// FIXME
		return null;
	}
	
}
