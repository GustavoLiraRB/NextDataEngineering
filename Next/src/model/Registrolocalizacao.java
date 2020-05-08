package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the registrolocalizacao database table.
 * 
 */
@Entity
@NamedQuery(name="Registrolocalizacao.findAll", query="SELECT r FROM Registrolocalizacao r")
public class Registrolocalizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name="_created")
	private Timestamp created;

	@Column(name="_updated")
	private Timestamp updated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private BigInteger hashdata;

	private String hashgeo;

	private double lat;

	private double lng;

	@Column(name="PESSOA_ID")
	private String pessoaId;

	@Column(name="TRANSMISSAO_ID")
	private String transmissaoId;

	//bi-directional many-to-one association to Situacao
	@ManyToOne
	private Situacao situacao;
	
	

	public Registrolocalizacao(Date data, BigInteger hashdata, String hashgeo, double lat, double lng, String pessoaId, Situacao situacao) {
		super();
		this.id = id;
		this.created = created;
		this.updated = updated;
		this.data = data;
		this.hashdata = hashdata;
		this.hashgeo = hashgeo;
		this.lat = lat;
		this.lng = lng;
		this.pessoaId = pessoaId;
		this.transmissaoId = transmissaoId;
		this.situacao = situacao;
	}

	public Registrolocalizacao() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigInteger getHashdata() {
		return this.hashdata;
	}

	public void setHashdata(BigInteger hashdata) {
		this.hashdata = hashdata;
	}

	public String getHashgeo() {
		return this.hashgeo;
	}

	public void setHashgeo(String hashgeo) {
		this.hashgeo = hashgeo;
	}

	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return this.lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getPessoaId() {
		return this.pessoaId;
	}

	public void setPessoaId(String pessoaId) {
		this.pessoaId = pessoaId;
	}

	public String getTransmissaoId() {
		return this.transmissaoId;
	}

	public void setTransmissaoId(String transmissaoId) {
		this.transmissaoId = transmissaoId;
	}

	public Situacao getSituacao() {
		return this.situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

}