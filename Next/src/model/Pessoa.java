package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the pessoa database table.
 * 
 */
@Entity
@NamedQuery(name="Pessoa.findAll", query="SELECT p FROM Pessoa p")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="PESSOA_ID")
	private String pessoaId;

	@Column(name="_created")
	private Timestamp created;

	@Column(name="_updated")
	private Timestamp updated;

	//bi-directional many-to-one association to Historicosituacao
	@OneToMany(mappedBy="pessoa")
	private List<Historicosituacao> historicosituacaos;

	//bi-directional many-to-one association to Limitesdeslocamento
	@OneToMany(mappedBy="pessoa")
	private List<Limitesdeslocamento> limitesdeslocamentos;

	//bi-directional many-to-one association to Situacao
	@ManyToOne
	private Situacao situacao;

	public Pessoa() {
	}

	public String getPessoaId() {
		return this.pessoaId;
	}

	public void setPessoaId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<Historicosituacao> getHistoricosituacaos() {
		return this.historicosituacaos;
	}

	public void setHistoricosituacaos(List<Historicosituacao> historicosituacaos) {
		this.historicosituacaos = historicosituacaos;
	}

	public Historicosituacao addHistoricosituacao(Historicosituacao historicosituacao) {
		getHistoricosituacaos().add(historicosituacao);
		historicosituacao.setPessoa(this);

		return historicosituacao;
	}

	public Historicosituacao removeHistoricosituacao(Historicosituacao historicosituacao) {
		getHistoricosituacaos().remove(historicosituacao);
		historicosituacao.setPessoa(null);

		return historicosituacao;
	}

	public List<Limitesdeslocamento> getLimitesdeslocamentos() {
		return this.limitesdeslocamentos;
	}

	public void setLimitesdeslocamentos(List<Limitesdeslocamento> limitesdeslocamentos) {
		this.limitesdeslocamentos = limitesdeslocamentos;
	}

	public Limitesdeslocamento addLimitesdeslocamento(Limitesdeslocamento limitesdeslocamento) {
		getLimitesdeslocamentos().add(limitesdeslocamento);
		limitesdeslocamento.setPessoa(this);

		return limitesdeslocamento;
	}

	public Limitesdeslocamento removeLimitesdeslocamento(Limitesdeslocamento limitesdeslocamento) {
		getLimitesdeslocamentos().remove(limitesdeslocamento);
		limitesdeslocamento.setPessoa(null);

		return limitesdeslocamento;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}