package it.com.gestioneaulastudenti;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.com.gestioneaulastudenti.model.AulaScolastica;
import it.com.gestioneaulastudenti.model.Studente;
import it.com.gestioneaulastudenti.service.IAulaScolasticaService;
import it.com.gestioneaulastudenti.service.IStudenteService;

@Service
public class BatteriaDiTestService {
	
	@Autowired
	private IStudenteService studenteService;
	
	@Autowired
	private IAulaScolasticaService aulaScolasticaService;
	
	public static final String INSERISCI_NUOVA_AULA = "INSERISCI_NUOVA_AULA";
	public static final String INSERISCI_NUOVO_STUDENTE = "INSERISCI_NUOVO_STUDENTE";
	public static final String CERCA_STUDENTE_BY_ID = "CERCA_STUDENTE_BY_ID";
	public static final String CERCA_AULA_BY_ID = "CERCA_AULA_BY_ID";
	public static final String CERCA_TUTTI_STUDENTI = "CERCA_TUTTI_STUDENTI";
	public static final String CERCA_TUTTE_AULE = "CERCA_TUTTE_AULE";
	public static final String AGGIORNA_STUDENTE = "AGGIORNA_STUDENTE";
	public static final String AGGIORNA_AULA = "AGGIORNA_AULA";
	public static final String RIMUOVI_STUDENTE = "RIMUOVI_STUDENTE";
	public static final String RIMUOVI_AULA = "RIMUOVI_AULA";
	public static final String FIND_BY_EXAMPLE_BY_NOME = "FIND_BY_EXAMPLE_BY_NOME";
	public static final String FIND_BY_EXAMPLE_BY_CAPIENZA = "FIND_BY_EXAMPLE_BY_CAPIENZA";
	public static final String FIND_BY_EXAMPLE_BY_AULA_FROM_STUDENTE = "FIND_BY_EXAMPLE_BY_AULA_FROM_STUDENTE";
	
	public void eseguiBatteriaDiTest(String casoDaTestare) {
		
		try {
			
			switch (casoDaTestare) {
			
			case INSERISCI_NUOVA_AULA:
				AulaScolastica aulaScolastica = new AulaScolastica("Aula777", "Topografia", 10);
				aulaScolasticaService.inserisciNuovo(aulaScolastica);
				System.out.println("Aula appena inserit: " + aulaScolastica);
				break;

			case INSERISCI_NUOVO_STUDENTE:
				Studente studente = new Studente("Giacomo", "Mangelli", new Date());
				studente.setAulaScolastica(aulaScolasticaService.caricaSingolaAulaScolastica(2L));
				studenteService.inserisciNuovo(studente);
				
			case CERCA_STUDENTE_BY_ID:
				System.out.println(studenteService.caricaSingoloStudente(1L));
				break;
				
			case CERCA_AULA_BY_ID:
				System.out.println(aulaScolasticaService.caricaSingolaAulaScolastica(1L));
				break;
				
			case CERCA_TUTTI_STUDENTI:
				System.out.println(studenteService.listAllStudenti());
				break;
				
			case CERCA_TUTTE_AULE:
				System.out.println(aulaScolasticaService.listAllAuleScolastiche());
				break;
				
			case AGGIORNA_STUDENTE:
				Studente studenteEsistente = studenteService.caricaSingoloStudente(1L);
				if (studenteEsistente != null) {
					studenteEsistente.setCognome("Vecchioni");
					studenteService.aggiorna(studenteEsistente);
				}
				break;
				
			case AGGIORNA_AULA:
				AulaScolastica aulaScolasticaEsistente = aulaScolasticaService.caricaSingolaAulaScolastica(1L);
				if (aulaScolasticaEsistente != null) {
					aulaScolasticaEsistente.setCodice("Aula34");
					aulaScolasticaService.aggiorna(aulaScolasticaEsistente);
				}
				break;
				
			case RIMUOVI_STUDENTE:
				Studente studenteTemp = studenteService.caricaSingoloStudente(9L);
				studenteService.rimuovi(studenteTemp);
				break;
				
			case RIMUOVI_AULA: 
				AulaScolastica aulaScolasticaTemp = aulaScolasticaService.caricaSingolaAulaScolastica(5L);
				aulaScolasticaService.rimuovi(aulaScolasticaTemp);
				break;
				
			case FIND_BY_EXAMPLE_BY_NOME:
				System.out.println("########### EXAMPLE ########################");
				Studente studenteExample = new Studente();
				studenteExample.setNome("Giacomo");
				for (Studente studenteItem : studenteService.findByExample(studenteExample)) {
					System.out.println(studenteItem);
				}
				break;
				
			case FIND_BY_EXAMPLE_BY_CAPIENZA:
				System.out.println("########### EXAMPLE ########################");
				AulaScolastica aulaExample = new AulaScolastica();
				aulaExample.setCapienza(2);
				for (AulaScolastica aulaItem : aulaScolasticaService.findByExample(aulaExample)) {
					System.out.println(aulaItem);
				}
				break;
				
			case FIND_BY_EXAMPLE_BY_AULA_FROM_STUDENTE:
				System.out.println("########### EXAMPLE ########################");
				Studente studenteExample2 = new Studente();
				studenteExample2.setAulaScolastica(aulaScolasticaService.caricaSingolaAulaScolastica(1L));
				for (Studente studenteItem : studenteService.findByExample(studenteExample2)) {
					System.out.println(studenteItem);
				}
				break;
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
