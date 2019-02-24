package es.upm.etsisi;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

public class Application implements CommandLineRunner {
	private static Scanner sc;
	@Value("${spring.data.mongodb.database}")
	private String mongodb_database;
	@Value("${spring.data.mongodb.uri}")
	private String mongodb_uri;
	@Value("${onesaitplatform.iotclient.urlRestIoTBroker}")
	private String urlRestIoTBroker;
	@Value("${onesaitplatform.iotclient.token}")
	private String sofia2_token;
	@Value("${onesaitplatform.iotclient.deviceTemplate}")
	private String sofia2_kp;
	@Value("${onesaitplatform.iotclient.device}")
	private String sofia2_instance;
	
	static {
		sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String opcionStr;
		do {
			mostrarMenu();
			opcionStr = sc.nextLine();
			switch (opcionStr) {
				case "1":
					urlRestIoTBroker = null;
					sofia2_token = null;
					sofia2_kp = null;
					sofia2_instance = null;
					MotaAzureApplication.main(args);
					break;
				case "2":
					urlRestIoTBroker = null;
					sofia2_token = null;
					sofia2_kp = null;
					sofia2_instance = null;
					OmJsonAzureApplication.main(args);
					break;
				case "3":
					mongodb_database = null;
					mongodb_uri = null;
					sofia2_token = "";
					sofia2_kp = "";
					sofia2_instance = ""; 
					MotaSofia2Application.main(args);
					break;
				case "4":
					mongodb_database = null;
					mongodb_uri = null;
					sofia2_token = "";
					sofia2_kp = "";
					sofia2_instance = "";
					OmJsonSofia2Application.main(args);
					break;
				case "0":
					System.out.println("Fin del programa.");
					System.exit(0);
					break;
				default:
					System.out.println("Entrada no valida.");
			}
		} while(!opcionStr.equals("1")
				|| !opcionStr.equals("2")
				|| !opcionStr.equals("3")
				|| !opcionStr.equals("4")
				|| !opcionStr.equals("0"));
	}
	
	/**
	 * Muestra el menú de operaciones por consola.
	 */
	private void mostrarMenu() {
		System.out.println("===================================================");
		System.out.println("Menu de despliegue de OM-Json Standarizer (v3)");
		System.out.println("1. CRUD con Azure Cosmos DB de trazas sin estandarizar.");
		System.out.println("2. CRUD con Azure Cosmos DB de trazas OM-Json.");
		System.out.println("3. CRUD con Sofia2 de trazas sin estandarizar.");
		System.out.println("4. CRUD con Sofia2 de trazas OM-Json.");
		System.out.println("0. Salir.");
		System.out.println("===================================================");
	}
	
}
