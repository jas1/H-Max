package sample1.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sample1.dao.EntityManagerMock;
import sample1.model.Cerveza;
import sample1.model.CervezaStock;
import sample1.model.Lugar;
import sample1.model.LugarStock;
import sample1.model.TipoLugar;

public class StockMockSimulator {

	
//	public static List<LugarStock> regenerateStock(List<Lugar> lugares) {
//		
//	}
	
	public static List<LugarStock> generarLugarStockPara(List<Lugar> lugares) {

		List<LugarStock> lugarStock = new ArrayList<>();

		for (Lugar lugar : lugares) {
			LugarStock tmpStock = new LugarStock();

			// stock de birras
			List<CervezaStock> initStock = iniciarCervezaStock(lugar);
			tmpStock.setListCerveza(initStock);
			
			// stock de personas
			tmpStock.setTimestampStock(new Date());
			
			Random rand = new Random();
			int max = 200;
			int min = 5;			
			tmpStock.setCantidadGente(rand.nextInt(max - min + 1) + min);
			tmpStock.setAproximadoEspera(tmpStock.getCantidadGente() / 30);
//			enti			
			lugarStock.add(tmpStock);
		}

		return lugarStock;
	}
	
	 private static List<CervezaStock> iniciarCervezaStock(Lugar tmpStock) {
		 
		List<CervezaStock> cantidad = EntityManagerMock.cervezasTipo.stream()
				.map(cervezaTipo -> crearCervezaStock(cervezaTipo,tmpStock))
				.collect(Collectors.toList());
		
		
		return cantidad;
	}

	private static CervezaStock crearCervezaStock(Cerveza cervezaTipo,Lugar tmpStock) {
		CervezaStock ret = new CervezaStock();
		ret.setCerveza(cervezaTipo);
		Random rnd = new Random();
		if (tmpStock.getTieneGrowler() && cervezaTipo.getAptoGrowler()) {
			ret.setHayGrowler(rnd.nextBoolean());	
		}else{
			ret.setHayGrowler(false);
		}
		
		if (cervezaTipo.getAptoTirada()) {
			ret.setHayTirada(rnd.nextBoolean());	
		}else{
			ret.setHayTirada(false);
		}
		
		Random rand = new Random();
		Double lambda=2d;
		
	    Double randCant =  Math.log(1-rand.nextDouble())/(-lambda);
	    randCant = randCant* 100; 
	    // para decir manejan cientos de litros de cerveza
		Integer result = randCant.intValue() ;
	    
	    // para decir manejan cientos de litros de cerveza
		ret.setCantidad(result);
				
		return ret;
	}

	public static List<Cerveza> listadoCervezas(){
			String fileName = "src/main/resources/patagonia_cervezas_levantar.txt";
			List<Cerveza> list = new ArrayList<>();

			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
				list = stream
						.map(StockMockSimulator::convertToCerveza)
						.collect(Collectors.toList());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return list;
	 }
	
	 private static Cerveza convertToCerveza(String string) {
			String[] tmpSplit = string.split("\t");
			Cerveza obj = new Cerveza();
			//nombre	descripcion	estilo	color	abv	volumen	urlImagen
			
			int i = 0;
			obj.setCodigo(tmpSplit[i++]);
			obj.setNombre(tmpSplit[i++]);
			obj.setDescripcion(tmpSplit[i++]);
			obj.setEstilo(tmpSplit[i++]);
			obj.setColor(tmpSplit[i++]);
			obj.setAbv(tmpSplit[i++]);
			obj.setVolumen(tmpSplit[i++]);
			obj.setUrlImagen(tmpSplit[i++]);
			
			Random rnd = new Random();			
			obj.setAptoBotella(true);
			obj.setAptoTirada(true);
			obj.setAptoGrowler(rnd.nextBoolean());
			
			return obj;
	 }
	 

	 public static List<Lugar> listadoLugares(){
//	public static void listadoLugares() {
		String fileName = "src/main/resources/patagonia_lugares_levantar.txt";
//		List<String> list = new ArrayList<>();
		List<Lugar> list = new ArrayList<>();

		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			// 1. filter line 3
			// 2. convert all content to upper case
			// 3. convert it into a List
			list = stream
					// .filter(line -> !line.startsWith("line3"))
					// .map(String::toUpperCase)
					.map(StockMockSimulator::convertToLugar)
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

//		list.forEach(lug -> System.out.println(HelperVarios.objectToJson(lug)));
		return list;
	}

	private static Lugar convertToLugar(String string) {

		// List<Lugar> result = new ArrayList<>();

		// for (String temp : string) {
		String[] tmpSplit = string.split("\t");
		Lugar obj = new Lugar();
		obj.setNombre(tmpSplit[0]);
		obj.setDireccion(tmpSplit[1]);
		if (tmpSplit.length==3) {
			obj.setTelefono(tmpSplit[2]);	
		}else{
			obj.setTelefono("");
		}
		
		obj.setDescripcion(tmpSplit[0]);

		if (tmpSplit[0].contains("BeerTruck")) {
			obj.setTipo(TipoLugar.BEERTRUCK);
			obj.setTieneMesas(false);
		} else {
			obj.setTipo(randomTipoLugar());

		}

		Random rnd = new Random();
		// obj.setTieneGrowler(rnd.nextBoolean());
		// obj.setTieneMesas(rnd.nextBoolean());

		switch (obj.getTipo()) {
		case BAR: {
			obj.setTieneGrowler(true);
			obj.setTieneMesas(true);
			obj.setTieneStreaming(rnd.nextBoolean());
		}
			break;
		case BEERTRUCK: {
			obj.setTieneGrowler(true);
			obj.setTieneMesas(false);
			obj.setTieneStreaming(false);
		}
			break;
		case CONTENEDOR: {
			obj.setTieneGrowler(rnd.nextBoolean());
			obj.setTieneMesas(false);
			obj.setTieneStreaming(false);
		}
		case JARDIN: {
			obj.setTieneGrowler(rnd.nextBoolean());
			obj.setTieneMesas(rnd.nextBoolean());
			obj.setTieneStreaming(false);
		}
		case FABRICA: {
			obj.setTieneGrowler(true);
			obj.setTieneMesas(true);
			obj.setTieneStreaming(true);
		}

		default:
			break;
		}
		// result.add(obj);
		// }

		return obj;

	}

	private static TipoLugar randomTipoLugar() {
		// // Jardin, Bar, beertruck , fabrica, contenedor

		// List<String> tmpSplit =
		// Arrays.asList(TipoLugar.values()).stream().map(x ->
		// x.name()).collect(Collectors.toList());
		List<TipoLugar> tmpSplit = new ArrayList<>();
		tmpSplit.add(TipoLugar.BAR);
		tmpSplit.add(TipoLugar.CONTENEDOR);
		tmpSplit.add(TipoLugar.FABRICA);
		tmpSplit.add(TipoLugar.JARDIN);
		
		Random rnd = new Random();

		return tmpSplit.get(rnd.nextInt(tmpSplit.size()));
	}

}
