package it.com.gestioneaulastudenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioneaulastudentiApplication implements CommandLineRunner {

	@Autowired
	private BatteriaDiTestService batteriaDiTestService;
	
	public static void main(String[] args) {
		SpringApplication.run(GestioneaulastudentiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		String casoDaTestare = BatteriaDiTestService.FIND_BY_EXAMPLE_BY_AULA_FROM_STUDENTE;
		
		System.out.println("################ START #################");
		System.out.println("################ eseguo il test " + casoDaTestare + "  #################");
		
		batteriaDiTestService.eseguiBatteriaDiTest(casoDaTestare);
		
		System.out.println("################ FINE #################");
		
	}

}
