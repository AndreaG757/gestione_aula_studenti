package it.com.gestioneaulastudenti.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.com.gestioneaulastudenti.dao.IAulaScolasticaDAO;
import it.com.gestioneaulastudenti.dao.IStudenteDAO;
import it.com.gestioneaulastudenti.model.AulaScolastica;
import it.com.gestioneaulastudenti.model.Studente;

@Service
public class AulaScolasticaServiceImpl implements IAulaScolasticaService {

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired
	private IAulaScolasticaDAO aulaScolasticaDAO;
	
	@Autowired
	private IStudenteDAO studenteDAO;
	
	@Transactional(readOnly = true)
	@Override
	public List<AulaScolastica> listAllAuleScolastiche() {
		return aulaScolasticaDAO.list();
	}

	@Transactional(readOnly = true)
	@Override
	public AulaScolastica caricaSingolaAulaScolastica(Long id) {
		return aulaScolasticaDAO.get(id);
	}

	@Transactional
	@Override
	public void aggiorna(AulaScolastica aulaScolasticaInstance) {
		aulaScolasticaDAO.update(aulaScolasticaInstance);
	}

	@Transactional
	@Override
	public void inserisciNuovo(AulaScolastica aulaScolasticaInstance) {
		aulaScolasticaDAO.insert(aulaScolasticaInstance);
	}

	@Transactional
	@Override
	public void rimuovi(AulaScolastica aulaScolasticaInstance) {
		
		List<Studente> studenti = studenteDAO.findEagerFetch(aulaScolasticaInstance.getId());
		aulaScolasticaInstance.setStudenti(studenti);
		
		if (!aulaScolasticaInstance.getStudenti().isEmpty())
			throw new RuntimeException("Non puoi eliminare l'aula perche' ha ancora degli studenti");
			
		aulaScolasticaDAO.delete(aulaScolasticaInstance);
	
	}

	@Transactional(readOnly = true)
	@Override
	public List<AulaScolastica> findByExample(AulaScolastica aulaScolasticaInstance) {
		return aulaScolasticaDAO.findByExample(aulaScolasticaInstance);
	}

}
