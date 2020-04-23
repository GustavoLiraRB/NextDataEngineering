package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the limitedeslocamento database table.
 * 
 */
@Entity
@NamedQuery(name="Limitedeslocamento.findAll", query="SELECT l FROM Limitedeslocamento l")
public class Limitedeslocamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private double lat1;

	private double lat2;

	private double lat3;

	private double lat4;

	private String limiteDeslocamentocol;

	private double lng1;

	private double lng2;

	private double lng3;

	private double lng4;

	public Limitedeslocamento() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLat1() {
		return this.lat1;
	}

	public void setLat1(double lat1) {
		this.lat1 = lat1;
	}

	public double getLat2() {
		return this.lat2;
	}

	public void setLat2(double lat2) {
		this.lat2 = lat2;
	}

	public double getLat3() {
		return this.lat3;
	}

	public void setLat3(double lat3) {
		this.lat3 = lat3;
	}

	public double getLat4() {
		return this.lat4;
	}

	public void setLat4(double lat4) {
		this.lat4 = lat4;
	}

	public String getLimiteDeslocamentocol() {
		return this.limiteDeslocamentocol;
	}

	public void setLimiteDeslocamentocol(String limiteDeslocamentocol) {
		this.limiteDeslocamentocol = limiteDeslocamentocol;
	}

	public double getLng1() {
		return this.lng1;
	}

	public void setLng1(double lng1) {
		this.lng1 = lng1;
	}

	public double getLng2() {
		return this.lng2;
	}

	public void setLng2(double lng2) {
		this.lng2 = lng2;
	}

	public double getLng3() {
		return this.lng3;
	}

	public void setLng3(double lng3) {
		this.lng3 = lng3;
	}

	public double getLng4() {
		return this.lng4;
	}

	public void setLng4(double lng4) {
		this.lng4 = lng4;
	}

}