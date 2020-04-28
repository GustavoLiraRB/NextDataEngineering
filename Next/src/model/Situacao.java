package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the situacao database table.
 * 
 */
@Entity
@NamedQuery(name="Situacao.findAll", query="SELECT s FROM Situacao s")
public class Situacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updated;

	private String descricao;

	//bi-directional many-to-one association to Historicosituacao
	@OneToMany(mappedBy="situacao")
	private List<Historicosituacao> historicosituacaos;

	//bi-directional many-to-one association to Pessoa
	@OneToMany(mappedBy="situacao")
	private List<Pessoa> pessoas;

	//bi-directional many-to-one association to Registrolocalizacao
	@OneToMany(mappedBy="situacao")
	private List<Registrolocalizacao> registrolocalizacaos;

	public Situacao() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Historicosituacao> getHistoricosituacaos() {
		return this.historicosituacaos;
	}

	public void setHistoricosituacaos(List<Historicosituacao> historicosituacaos) {
		this.historicosituacaos = historicosituacaos;
	}

	public Historicosituacao addHistoricosituacao(Historicosituacao historicosituacao) {
		getHistoricosituacaos().add(historicosituacao);
		historicosituacao.setSituacao(this);

		return historicosituacao;
	}

	public Historicosituacao removeHistoricosituacao(Historicosituacao historicosituacao) {
		getHistoricosituacaos().remove(historicosituacao);
		historicosituacao.setSituacao(null);

		return historicosituacao;
	}

	public List<Pessoa> getPessoas() {
		return this.pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public Pessoa addPessoa(Pessoa pessoa) {
		getPessoas().add(pessoa);
		pessoa.setSituacao(this);

		return pessoa;
	}

	public Pessoa removePessoa(Pessoa pessoa) {
		getPessoas().remove(pessoa);
		pessoa.setSituacao(null);

		return pessoa;
	}

	public List<Registrolocalizacao> getRegistrolocalizacaos() {
		return this.registrolocalizacaos;
	}

	public void setRegistrolocalizacaos(List<Registrolocalizacao> registrolocalizacaos) {
		this.registrolocalizacaos = registrolocalizacaos;
	}

	public Registrolocalizacao addRegistrolocalizacao(Registrolocalizacao registrolocalizacao) {
		getRegistrolocalizacaos().add(registrolocalizacao);
		registrolocalizacao.setSituacao(this);

		return registrolocalizacao;
	}

	public Registrolocalizacao removeRegistrolocalizacao(Registrolocalizacao registrolocalizacao) {
		getRegistrolocalizacaos().remove(registrolocalizacao);
		registrolocalizacao.setSituacao(null);

		return registrolocalizacao;
	}

}