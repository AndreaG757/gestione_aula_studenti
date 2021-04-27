package it.com.gestioneaulastudenti.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.com.gestioneaulastudenti.dao.IStudenteDAO;
import it.com.gestioneaulastudenti.model.Studente;

@Service
public class StudenteServiceImpl implements IStudenteService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private IStudenteDAO studenteDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<Studente> listAllStudenti() {
		return studenteDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public Studente caricaSingoloStudente(Long id) {
		return studenteDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(Studente studenteInstance) {
		studenteDAO.update(studenteInstance);
	}

	@Transactional
	@Override
	public void inserisciNuovo(Studente studenteInstance) throws Exception {
		List<Studente> studenti = studenteDAO.findEagerFetch(studenteInstance.getAulaScolastica().getId());
		if (studenti.size() < studenteInstance.getAulaScolastica().getCapienza())
			studenteDAO.insert(studenteInstance);
		else 
			throw new Exception("L'aula e' piena!");
	}

	@Transactional
	@Override
	public void rimuovi(Studente studenteInstance) {
		studenteDAO.delete(studenteInstance);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Studente> findByExample(Studente studenteInstance) {
		return studenteDAO.findByExample(studenteInstance);
	}

}
