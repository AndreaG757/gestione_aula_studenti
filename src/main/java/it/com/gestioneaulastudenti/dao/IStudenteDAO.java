package it.com.gestioneaulastudenti.dao;

import java.util.List;

import it.com.gestioneaulastudenti.model.Studente;

public interface IStudenteDAO extends IBaseDAO<Studente> {

	public List<Studente> findEagerFetch(long id);
	
}
