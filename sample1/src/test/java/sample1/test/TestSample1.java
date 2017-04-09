package sample1.test;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import sample1.helpers.HelperVarios;
import sample1.helpers.StockMockSimulator;

public class TestSample1 {

	@Test
	public void test1() {
		System.out.println("Sambungo!");
		
		StockMockSimulator.listadoLugares().forEach(lug -> System.out.println(HelperVarios.objectToJson(lug)));
		
		StockMockSimulator.listadoCervezas().forEach(lug -> System.out.println(HelperVarios.objectToJson(lug)));
		
		String probandoTipo = "tipo1-tipo2-tipo3";
		Arrays.asList(probandoTipo.split("-")).stream().forEach(System.out::println);

	}
	
	@Test
	public void testDistro() {
//		for (int i = 0; i < 100; i++) {
//			System.out.println(getNext());
//			
//		}
		Random rand = new Random();
		int max = 200;
		int min = 5;
		;
		for (int i = 0; i < 100; i++) {
			System.out.println(rand.nextInt(max - min + 1) + min);
		}
	}
	
	public double getNext() {
		
		Random rand = new Random();
		Double lambda=2d;
		
	    Double randCant =  Math.log(1-rand.nextDouble())/(-lambda);
	    randCant = randCant* 100; 
	    // para decir manejan cientos de litros de cerveza
		Integer result = randCant.intValue() ;
		return randCant;
	}	
	
}
