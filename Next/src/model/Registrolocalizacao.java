package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registrolocalizacao database table.
 * 
 */
@Entity
@NamedQuery(name="Registrolocalizacao.findAll", query="SELECT r FROM Registrolocalizacao r")
public class Registrolocalizacao implements Serializable {
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

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Lob
	private String hashdata;

	private String hashgeo;

	private double lat;

	private double lng;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	private Pessoa pessoa;

	//bi-directional many-to-one association to Situacao
	@ManyToOne
	private Situacao situacao;

	public Registrolocalizacao() {
	}
	
	

	public Registrolocalizacao(Date data, String hashdata, String hashgeo, double lat, double lng, Pessoa pessoa,
			Situacao situacao) {
		super();
		this.data = data;
		this.hashdata = hashdata;
		this.hashgeo = hashgeo;
		this.lat = lat;
		this.lng = lng;
		this.pessoa = pessoa;
		this.situacao = situacao;
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

	public String getHashdata() {
		return this.hashdata;
	}

	public void setHashdata(String hashdata) {
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