package it.com.gestioneaulastudenti.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "aula_scolastica")
public class AulaScolastica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "codice")
	private String codice;
	
	@Column(name = "materia")
	private String materia;
	
	@Column(name = "capienza")
	private Integer capienza;
	
	@OneToMany(mappedBy = "aulaScolastica", fetch = FetchType.LAZY)
	private List<Studente> studenti = new ArrayList<>();
	
	public AulaScolastica() {}

	public AulaScolastica(String codice, String materia, Integer capienza) {
		this.codice = codice;
		this.materia = materia;
		this.capienza = capienza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	public List<Studente> getStudenti() {
		return studenti;
	}

	public void setStudenti(List<Studente> studenti) {
		this.studenti = studenti;
	}

	@Override
	public String toString() {
		return "AulaScolastica [id=" + id + ", codice=" + codice + ", materia=" + materia
				+ ", capienza=" + capienza + "]";
	}
	
}