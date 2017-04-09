package sample1.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import sample1.dao.EntityManagerMock;
import sample1.model.Cerveza;
import sample1.model.CervezaStock;
import sample1.model.Lugar;
import sample1.model.LugarStock;
import sample1.model.TipoLugar;

public class StockMockSimulator {

	public static List<LugarStock> generarLugarStockPara(List<Lugar> lugares) {

		List<LugarStock> lugarStock = new ArrayList<>();

		for (Lugar lugar : lugares) {
			LugarStock tmpStock = new LugarStock();
			tmpStock.setLugar(lugar);
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

	
	public static List<String> listadoCervezasFromString(){
		
		List<String> ret = new ArrayList<>();
		String tmp = "AMBR\tCerveza Patagonia Amber Lager 740ml\tPatagonia Amber Lager es la primera variedad que la marca lanz\u00F3. Es una cerveza con una combinaci\u00F3n de l\u00FApulos patag\u00F3nicos y un blend de finas maltas que generan su color rojizo, un delicado aroma y un amargor apacible que permite dar a luz a un tostado delicioso. Ideal para acompa\u00F1ar carnes asadas y pastas con salsas especiadas. \tLAGER \t\u00C1MBAR \t4.50%\t740ML\thttps://cdn.shopify.com/s/files/1/1103/5152/products/Patagonia-Amber-Larger-1000x1467_1024x1024_10b329a6-d70d-4408-b697-343e841337ff_1024x1024.png?v=1465834626\r\nKUNE\tCerveza Patagonia K\u00FCn\u00E9 710ml\tPatagonia K\u00FCn\u00E9 es una cerveza de aroma intenso, leve amargor y notas herbales otorgados por el l\u00FApulo patag\u00F3nico. Combina una variedad de maltas especiales con una fermentaci\u00F3n a altas temperaturas, dando lugar a una Pale Ale de color dorado bronce brillante y un cuerpo con mucha personalidad. Ideal para acompa\u00F1ar carnes a la parrilla y pescados grasos.\tALE, PALE ALE\tDORADO \t5.00%\t710ML\thttps://cdn.shopify.com/s/files/1/1103/5152/products/Patagonia-Kune-1000x1467_987808b4-187e-4a71-a7f3-fd05793467c7_1024x1024.png?v=1465834661\r\nWIES\tCerveza Patagonia Weisse 740ml\tPatagonia Weisse es una cerveza tipo Wit que combina trigo y l\u00FApulo patag\u00F3nico otorg\u00E1ndole un color dorado suave. Se caracteriza por su aroma a frutas c\u00EDtricas compuesto por c\u00E1scaras de naranja, el especiado por las semillas de coriandro y las notas de clavo de olor. Su frescura y acidez media es el maridaje perfecto para comidas picantes o especiadas.\tLAGER\tDORADO \t5.00%\t740ML\thttps://cdn.shopify.com/s/files/1/1103/5152/products/Patagonia-Weisse-1000x1467_1024x1024.png?v=1465834681\r\nBOHE\tCerveza Patagonia Bohemian Pilsener 740ml\tLa Bohemian Pilsener es un estilo que naci\u00F3 en la ciudad de Bohemia, pero que r\u00E1pidamente gan\u00F3 popularidad en Europa, por ser una cerveza un poco m\u00E1s lupulada que una t\u00EDpica lager de Munich. Patagonia Bohemian Pilsener es una cerveza que respeta de manera excepcional este estilo, con un proceso de macerado de sus maltas y l\u00FApulo patag\u00F3nico que origina un color dorado brillante, gran cuerpo y un amargor equilibrado. Es ideal para acompa\u00F1ar carnes blancas.\tLAGER\tPAJIZO \t5.20%\t740ML \thttps://cdn.shopify.com/s/files/1/1103/5152/products/Patagonia-B-Pilsener-1000x1467_1024x1024.png?v=1465834640\r\nIPA24\tCerveza Patagonia 24.7 - Session IPA con Sauco 710ml\tUna nueva variedad de Patagonia, creada en la cervecer\u00EDa de Circuito Chico, justamente, en el KM 24,7. De un color dorado ayudado por el sauco es, como toda session beer, una cerveza de menor contenido alcoh\u00F3lico. Conservando un intenso aroma a l\u00FApulo gracias a su t\u00E9cnica de Dry Hopping, combina una entrada cargada de amargor y un dejo dulce en el final a partir del sauco a\u00F1adido en fr\u00EDo. Patagonia 24.7 es una cerveza refrescante, f\u00E1cil de tomar, pero cargada de personalidad.\tALE, IPA \tDORADO \t4.50%\t710ML\thttps://cdn.shopify.com/s/files/1/1103/5152/products/Patagonia-24-7-1000x1467_1024x1024.jpg?v=1483734202";
		String line;
		try (BufferedReader br = new BufferedReader(new StringReader(tmp))) {
		    while((line = br.readLine()) != null){
		    	ret.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret ;
	}
	
	public static List<String> listadoLugaresFromString(){
		
		List<String> ret = new ArrayList<>();
		String tmp = "Facultad de Medicina\tPasteur 706\tTel. + 54 911 4871 6070\tCABA\t-34.601015\t-58.399822\r\nSan Telmo \u2013 Per\u00FA\tPer\u00FA 602\tTel. + 54 911 2083 0749\tCABA\t-34.614933\t-58.374582\r\nSan Telmo \u2013 Plaza Dorrego\tDon Anselmo Aieta 1081\t--\tCABA\t-34.620459\t-58.372106\r\nC\u00F3rdoba y Mario Bravo\tAv. C\u00F3rdoba 3573\tTel. + 54 911 2085 6098\tCABA\t-34.59781\t-58.416857\r\nParaguay y Uruguay\tParaguay 1448\tTel. + 54 911 2085 9696\tCABA\t-34.598389\t-58.387556\r\nCallao y Viamonte\tCallao 650\tTel. + 54 911 2091 3982\tCABA\t-34.601463\t-58.39267\r\nParaguay y Riobamba\tParaguay 1900\tTel. + 54 911 2091 9641\tCABA\t-34.5987\t-58.394537\r\nDistrito Arcos\tParaguay 4979 - Centro Comercial Distrito Arcos\t--\tCABA\t-34.581105\t-58.42842\r\nArenales\tArenales 2707\t--\tCABA\t-34.592387\t-58.404238\r\nCa\u00F1itas\tAndres Arguibel 2831\t--\tCABA\t-34.572423\t-58.429789\r\nAv. de Mayo\tAv de Mayo N\u00B0 702\t--\tCABA\t-34.608831\t-58.376403\r\nBuen Pastor\tAvenida Hipolito Yrigoyen 325, Paseo del Buen Pastor\t--\tC\u00F3rdoba\t-31.423985\t-64.187019\r\nRecta Martinoli\tAv.Recta Martinoli 8627\t--\tC\u00F3rdoba\t-31.345691\t-64.28459\r\nIndependencia\tIndependencia 1073\t--\tC\u00F3rdoba\t-31.428148\t-64.189142\r\nMaschwitz - Jard\u00EDn Cervecero\tMendoza 1748, Ing. Maschwitz\t--\tZona Norte\t-34.383342\t-58.748374\r\nNordelta\tCentro Comercial Nordelta - Av. de los lagos 7008\tTel. + 54 911 4871 6070\tZona Norte\t-34.399623\t-58.653521\r\nMar del Plata\tComplejo la Normandina - Bv. Maritimo Patricio Peralta Ramos 5050\t--\tMar del Plata\t-38.026029\t-57.530377\r\nCaril\u00F3\tPaseo Las Alondras - Divisadero e/Casuarina y Cerezo\t--\tCaril\u00F3\t-37.164779\t-56.903154\r\nCity Bell\tCantilo 1011\t--\tZona Sur\t-34.87529\t-58.048968\r\nPinamar\tAv. del Mar 852\t--\tPinamar\t-37.108616\t-56.847855\r\nLa Plata 54 y 9\tCalle 54 num. 495\t--\tLa Plata\t-34.914689\t-57.945011\r\nLa Plata 7 y 59\tCalle 59 num. 591\t--\tLa Plata\t-34.919845\t-57.942523\r\nPocitos Nuevo\tLuis Alberto de Herrera 1193 Av.\tTel. + 598 2622 9812\tUruguay\t-34.905068\t-56.137184\r\nCarrasco\tAlfredo Arocena 1559\tTel. + 598 2600 5910\tUruguay\t-34.89031\t-56.056463\r\nMercado del Puerto\tMercado del Puerto, Ciudad Vieja\t--\tUruguay\t-34.905705\t-56.211728\r\nMatisse Mall\tParque Miramar\t--\tUruguay\t-34.865327\t-56.033949\r\nRecoleta\tAv. Alfredo Arocena 1559\tTel. + 0994 785341\tParaguay\t-25.300041\t-57.582201\r\nCarmelitas\tDr. Morra 832 esq. Hector Vera\tTel. + 0994 785341\tParaguay\t-25.287239\t-57.575768\r\nBeerTruck1\tmovil\t1555531234\tmovil\t-34.5987\t-58.394537\r\nBeerTruck2\tmovil\t1553531234\tmovil\t-34.601015\t-58.399822";
		String line;
		try (BufferedReader br = new BufferedReader(new StringReader(tmp))) {
		    while((line = br.readLine()) != null){
		    	ret.add(line);
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret ;
	}
	
	public static List<Cerveza> listadoCervezas(){
//			String fileName = "src/main/resources/patagonia_cervezas_levantar.txt";
			List<Cerveza> list = new ArrayList<>();

			List<String> tmp = listadoCervezasFromString();
			list = tmp.stream()
			.map(StockMockSimulator::convertToCerveza)
			.collect(Collectors.toList());
			
			
//			try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//				list = stream
//						.map(StockMockSimulator::convertToCerveza)
//						.collect(Collectors.toList());
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
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
//			obj.setUrlImagen(tmpSplit[i++]);
			obj.setUrlImagen("/images/"+obj.getCodigo()+".png");
			
			Random rnd = new Random();			
			obj.setAptoBotella(true);
			obj.setAptoTirada(true);
			obj.setAptoGrowler(rnd.nextBoolean());
			
			return obj;
	 }
	 

	 public static List<Lugar> listadoLugares(){
//	public static void listadoLugares() {
//		String fileName = "src/main/resources/patagonia_lugares_levantar.txt";
//		List<String> list = new ArrayList<>();
		List<Lugar> list = new ArrayList<>();
		
		List<String> tmp = listadoLugaresFromString();
		list = tmp.stream()
		.map(StockMockSimulator::convertToLugar)
		.collect(Collectors.toList());
		
//		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
//
//			// 1. filter line 3
//			// 2. convert all content to upper case
//			// 3. convert it into a List
//			list = stream
//					// .filter(line -> !line.startsWith("line3"))
//					// .map(String::toUpperCase)
//					.map(StockMockSimulator::convertToLugar)
//					.collect(Collectors.toList());
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

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

		obj.setGrupo(tmpSplit[3]);
		
		obj.setX(Double.valueOf(tmpSplit[4]));
		
		obj.setY(Double.valueOf(tmpSplit[5]));

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
