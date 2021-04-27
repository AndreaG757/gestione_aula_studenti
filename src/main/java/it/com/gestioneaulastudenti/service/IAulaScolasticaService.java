package it.com.gestioneaulastudenti.service;

import java.util.List;

import it.com.gestioneaulastudenti.model.AulaScolastica;

public interface IAulaScolasticaService {

	public List<AulaScolastica> listAllAuleScolastiche();

	public AulaScolastica caricaSingolaAulaScolastica(Long id);

	public void aggiorna(AulaScolastica aulaScolasticaInstance);

	public void inserisciNuovo(AulaScolastica aulaScolasticaInstance);

	public void rimuovi(AulaScolastica aulaScolasticaInstance);

	public List<AulaScolastica> findByExample(AulaScolastica aulaScolasticaInstance);
	
}
