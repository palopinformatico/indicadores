package com.microservice.indicadores;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class IndicadoresApplication {

	@GetMapping("/todosIndicadores")
	public StringBuilder todosIndicadores() {
		StringBuilder informationString = new StringBuilder();
		
		informationString = invocacion("https://www.mindicador.cl/api");
		
		return informationString;
	}

	@GetMapping("/uf")
	public StringBuilder uf() {
		StringBuilder informationString = new StringBuilder();
		
		informationString = invocacion("https://www.mindicador.cl/api/uf");
		
		return informationString;
	}

	@GetMapping("/ufFecha")
	public StringBuilder ufFecha() {
		StringBuilder informationString = new StringBuilder();
		
		LocalDateTime fechaHoy = LocalDateTime.now();
			
		int dia = fechaHoy.getDayOfMonth();
		int mes = fechaHoy.getMonthValue();
		int anio = fechaHoy.getYear();
		
		String strDia = dia < 10 ? "0" + dia : Integer.toString(dia);
		String strMes = mes < 10 ? "0" + mes : Integer.toString(mes);
		String strAnio = Integer.toString(anio);
		
		String fecha = strDia + "-" + strMes + "-" + strAnio;
		
		System.out.println(fecha);
		
		informationString = invocacion("https://www.mindicador.cl/api/uf/"+fecha);
		
		return informationString;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(IndicadoresApplication.class, args);
	}
	
	public static StringBuilder invocacion(String urlConeccion) {
		StringBuilder informationString = new StringBuilder();
		try {
			URL url = new URL(urlConeccion);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			if(responseCode != 200) {
				throw new RuntimeException("Ocurrió un error: " + responseCode);
			}
			else {
				
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				
				scanner.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return informationString;
		
	}

}
