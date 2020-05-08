package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historicosituacao database table.
 * 
 */
@Entity
@NamedQuery(name="Historicosituacao.findAll", query="SELECT h FROM Historicosituacao h")
public class Historicosituacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_created")
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_updated")
	private Date updated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="PESSOA_ID")
	private Pessoa pessoa;

	//bi-directional many-to-one association to Situacao
	@ManyToOne
	private Situacao situacao;

	public Historicosituacao() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return this.updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}