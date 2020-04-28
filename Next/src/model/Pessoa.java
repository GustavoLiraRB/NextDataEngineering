package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
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
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_created", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="_updated", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date updated;

	//bi-directional many-to-one association to Historicosituacao
	@OneToMany(mappedBy="pessoa")
	private List<Historicosituacao> historicosituacaos;

	//bi-directional many-to-one association to Limitesdeslocamento
	@OneToMany(mappedBy="pessoa")
	private List<Limitesdeslocamento> limitesdeslocamentos;

	//bi-directional many-to-one association to Situacao
	@ManyToOne
	private Situacao situacao;

	//bi-directional many-to-one association to Registrolocalizacao
	@OneToMany(mappedBy="pessoa")
	private List<Registrolocalizacao> registrolocalizacaos;

	public Pessoa() {
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

	public List<Registrolocalizacao> getRegistrolocalizacaos() {
		return this.registrolocalizacaos;
	}

	public void setRegistrolocalizacaos(List<Registrolocalizacao> registrolocalizacaos) {
		this.registrolocalizacaos = registrolocalizacaos;
	}

	public Registrolocalizacao addRegistrolocalizacao(Registrolocalizacao registrolocalizacao) {
		getRegistrolocalizacaos().add(registrolocalizacao);
		registrolocalizacao.setPessoa(this);

		return registrolocalizacao;
	}

	public Registrolocalizacao removeRegistrolocalizacao(Registrolocalizacao registrolocalizacao) {
		getRegistrolocalizacaos().remove(registrolocalizacao);
		registrolocalizacao.setPessoa(null);

		return registrolocalizacao;
	}

}