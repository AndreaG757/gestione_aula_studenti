package it.com.gestioneaulastudenti.dao;

import it.com.gestioneaulastudenti.model.AulaScolastica;

public interface IAulaScolasticaDAO extends IBaseDAO<AulaScolastica> {

	AulaScolastica findEagerFetch(long idAulaScolastica);

}
