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

import it.com.gestioneaulastudenti.model.Studente;

@Component
public class StudenteDAOImpl implements IStudenteDAO {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public List<Studente> list() {
		return entityManager.createQuery("from Studente", Studente.class).getResultList();
	}

	@Override
	public Studente get(Long id) {
		return entityManager.find(Studente.class, id);
	}

	@Override
	public void update(Studente object) {
		object = entityManager.merge(object);
	}

	@Override
	public void insert(Studente object) {
		entityManager.persist(object);
	}

	@Override
	public void delete(Studente object) {
		entityManager.remove(entityManager.merge(object));
	}
	
	@Override
	public List<Studente> findEagerFetch(long id) {
		List<Studente> studenti = entityManager.createQuery("FROM Studente s JOIN FETCH s.aulaScolastica a where a.id = :id", Studente.class)
				.setParameter("id", id)
				.getResultList();
		return studenti;
	}

	@Override
	public List<Studente> findByExample(Studente object) {
		
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Studente s where s.id = s.id ");

		if (StringUtils.isNotEmpty(object.getNome())) {
			whereClauses.add(" s.nome like :nome ");
			paramaterMap.put("nome", "%" + object.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(object.getCognome())) {
			whereClauses.add(" s.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + object.getCognome() + "%");
		}
		if (object.getDataNascita() != null) {
			whereClauses.add(" s.dataNascita = :dataNascita ");
			paramaterMap.put("dataNascita", object.getDataNascita());
		} 
		if (object.getAulaScolastica() != null) {
			whereClauses.add(" s.aulaScolastica = :aulaScolastica ");
			paramaterMap.put("aulaScolastica", object.getAulaScolastica());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Studente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Studente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
		
	}

}
