package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the limitesdeslocamento database table.
 * 
 */
@Entity
@NamedQuery(name="Limitesdeslocamento.findAll", query="SELECT l FROM Limitesdeslocamento l")
public class Limitesdeslocamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String hashgeo;

	private double lat1;

	private double lng1;

	private double raio;

	//bi-directional many-to-one association to Pessoa
	@ManyToOne
	@JoinColumn(name="PESSOA_ID")
	private Pessoa pessoa;

	public Limitesdeslocamento() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHashgeo() {
		return this.hashgeo;
	}

	public void setHashgeo(String hashgeo) {
		this.hashgeo = hashgeo;
	}

	public double getLat1() {
		return this.lat1;
	}

	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}

	public double getLng1() {
		return this.lng1;
	}

	public void setLng1(double lng1) {
		this.lng1 = lng1;
	}

	public double getRaio() {
		return this.raio;
	}

	public void setRaio(double raio) {
		this.raio = raio;
	}

	public Pessoa getPessoa() {
		return this.pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}