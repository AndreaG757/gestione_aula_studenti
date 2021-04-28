package it.com.gestioneaulastudenti.service;

import java.util.List;

import it.com.gestioneaulastudenti.model.Studente;

public interface IStudenteService {

	public List<Studente> listAllStudenti();

	public Studente caricaSingoloStudente(Long id);

	public void aggiorna(Studente studenteInstance) throws Exception;

	public void inserisciNuovo(Studente studenteInstance) throws Exception;

	public void rimuovi(Studente studenteInstance);

	public List<Studente> findByExample(Studente studenteInstance);
	
}
