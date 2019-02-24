package es.upm.etsisi;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.core.JsonProcessingException;

import es.upm.etsisi.entities.mota.MotaMeasureTraza;
import es.upm.etsisi.services.GestorMotaMeasures;

@SpringBootApplication
public class OmJsonSofia2Application implements CommandLineRunner {
	@Autowired
	private GestorMotaMeasures gestorMotas;
	private static Scanner sc;
	
	static {
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OmJsonSofia2Application.class, args);
		System.exit(0);
	}
	
	@Override
	public void run(String... args) throws Exception {
		String opcionStr;
		do {
			mostrarMenu();
			opcionStr = sc.nextLine();
			switch (opcionStr) {
			case "1":
				generarTrazas();
				break;
			case "2":
				altaTraza();
				break;
			case "3":
				bajaTraza();
				break;
			case "4":
				buscarTraza();
				break;
			case "5":
				listarTrazas();
				break;
			case "0":
				System.out.println("Fin del programa.");
				break;
			default:
				System.out.println("Entrada no valida.");
			}	
		} while(!opcionStr.equals("0"));
	}
	
	/**
	 * Muestra el menú de operaciones por consola.
	 */
	private void mostrarMenu() {
		System.out.println("\nSeleccione una opcion...");
		System.out.println("1. Generar trazas con valores aleatorios de motas a fichero (motaMeasures.json).");
		System.out.println("2. Alta en Azure Cosmos DB de una nueva traza de mota.");
		System.out.println("3. Baja de Azure Cosmos DB de una traza de mota.");
		System.out.println("4. Buscar una traza de mota en Azure Cosmos DB.");
		System.out.println("5. Listar todas las trazas de motas de Azure Cosmos DB.");
		System.out.println("0. Salir.");
	}
	
	/**
	 * @throws UnsupportedEncodingException
	 * @throws JsonProcessingException
	 * @throws ParseException
	 */
	private void generarTrazas() throws UnsupportedEncodingException, JsonProcessingException, ParseException {
		gestorMotas.generateMotaMeasuresToFile();
	}
	
	/**
	 * Operación para dar de alta una traza de mota.
	 */
	private void altaTraza() {
		MotaMeasureTraza motaTraza = new MotaMeasureTraza();
		if(gestorMotas.altaMota(motaTraza)) {
			System.out.println("Traza dada de alta en la base de datos.");
		} else {
			System.out.println("No se ha podido dar de alta la traza en la base de datos.");
		}
	}
	
	/**
	 * Operación para dar de baja una traza de mota.
	 */
	private void bajaTraza() {
		System.out.println("Introduzca el id a buscar para dar de baja: ");
		String id = sc.nextLine();
		if(gestorMotas.bajaMota(id)) {
			System.out.println("Traza dada de baja de la base de datos.");
		} else {
			System.out.println("No se ha podido dar de baja a la traza en la base de datos.");
		}
	}
	
	/**
	 * Operación para buscar una traza de mota.
	 */
	private void buscarTraza() {
		System.out.println("Introduzca el id a buscar: ");
		String id = sc.nextLine();
		Optional<MotaMeasureTraza> motaTraza = gestorMotas.getMotaTraza(id);
		if(motaTraza != null) {
			System.out.println("Traza encontrada:");
			System.out.println(motaTraza.toString());
		} else {
			System.out.println("La traza con el id " + id + " no se encuentra en la base de datos.");
		}
	}
	
	/**
	 * Operación para listar todas las trazas de motas.
	 */
	private void listarTrazas() {
		Iterable<MotaMeasureTraza> listaMotas = gestorMotas.getListaMotaTrazas();
		if(listaMotas != null) {
			System.out.println(listaMotas.toString());
		} else {
			System.out.println("Lista de trazas vacia.");
		}
	}

}
