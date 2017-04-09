package sample1.test;

import java.util.Arrays;

import org.junit.Test;

import sample1.service.StockService;

public class ServiciosRestTest {

	StockService stockService = new StockService();
	
	@Test
	public void testRest1(){
	//		mostrarCervezasDisponibles(); 

		
		
			
	}
	
	@Test
	public void testRest2(){
//		mostrarCervezasDisponibles(); 
		
		String probandoTipo = "tipo1-tipo2-tipo3";
		Arrays.asList(probandoTipo.split("-")).stream().forEach(System.out::println);

		
	}

	
}
