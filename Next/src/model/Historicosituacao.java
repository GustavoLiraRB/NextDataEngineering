package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the historicosituacao database table.
 * 
 */
@Entity
@NamedQuery(name="Historicosituacao.findAll", query="SELECT h FROM Historicosituacao h")
public class Historicosituacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private Timestamp data;

	private String pessoa;

	public Historicosituacao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getData() {
		return this.data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public String getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(String pessoa) {
		this.pessoa = pessoa;
	}

}