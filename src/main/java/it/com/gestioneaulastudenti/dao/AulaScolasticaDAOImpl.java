package it.com.gestioneaulastudenti.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import it.com.gestioneaulastudenti.model.AulaScolastica;

@Component
public class AulaScolasticaDAOImpl implements IAulaScolasticaDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<AulaScolastica> list() {
		return entityManager.createQuery("from AulaScolastica", AulaScolastica.class).getResultList();
	}

	@Override
	public AulaScolastica get(Long id) {
		return entityManager.find(AulaScolastica.class, id);
	}

	@Override
	public void update(AulaScolastica object) {
		object = entityManager.merge(object);
	}

	@Override
	public void insert(AulaScolastica object) {
		entityManager.persist(object);
	}

	@Override
	public void delete(AulaScolastica object) {
		entityManager.remove(entityManager.merge(object));
	}
	
	@Override
	public AulaScolastica findEagerFetch(long idAulaScolastica) {
		return entityManager.createQuery("SELECT a FROM AulaScolastica a JOIN FETCH a.studenti s WHERE a.id = :id", AulaScolastica.class)
				.setParameter("id", idAulaScolastica)
				.getResultStream().findFirst().orElse(null);
	}

	@Override
	public List<AulaScolastica> findByExample(AulaScolastica object) {

		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select a from AulaScolastica a where a.id = a.id ");

		if (StringUtils.isNotEmpty(object.getCodice())) {
			whereClauses.add(" a.codice like :codice ");
			paramaterMap.put("codice", "%" + object.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(object.getMateria())) {
			whereClauses.add(" a.materia like :materia ");
			paramaterMap.put("materia", object.getMateria());
		}
		if (object.getCapienza() != null) {
			whereClauses.add(" a.capienza >= :capienza ");
			paramaterMap.put("capienza", + object.getCapienza());
		} 
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<AulaScolastica> typedQuery = entityManager.createQuery(queryBuilder.toString(), AulaScolastica.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
		
	}

}
