package com.microservice.indicadores;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IndicadoresApplicationTests {

	@Test
	public void testPrueba() {
		StringBuilder informationString = new StringBuilder();
		
		informationString = IndicadoresApplication.invocacion("https://www.mindicador.cl/api");
		
		System.out.println(informationString);
		
		Assertions.assertTrue(informationString.length() > 0, "Si la longitud es > 0 es porque la variable contiene datos");
	}

}
