package com.proyectoFinal.teams.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





@Entity
@Table(name= "teams")
public class Team implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@Column(nullable=false)
	private String himno;
	
	@Column(nullable=false)
	private String city;
	
	@Column(nullable=false)
	private String country;
	
	@Column(nullable=false)
	private Integer uclTrophies;
	
	private String image;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
	
	public Date getCreatedAt() {
		return createdAt;
	}







	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public String getImage() {
		return image;
	}







	public void setImage(String imagen) {
		this.image = imagen;
	}
	
	public Long getId() {
		return id;
	}







	public void setId(Long id) {
		this.id = id;
	}







	public String getName() {
		return name;
	}







	public void setName(String name) {
		this.name = name;
	}







	public String getHimno() {
		return himno;
	}







	public void setHimno(String himno) {
		this.himno = himno;
	}







	public String getCity() {
		return city;
	}







	public void setCity(String city) {
		this.city = city;
	}







	public String getCountry() {
		return country;
	}







	public void setCountry(String country) {
		this.country = country;
	}







	public Integer getUclTrophies() {
		return uclTrophies;
	}







	public void setUclTrophies(Integer uclTrophies) {
		this.uclTrophies = uclTrophies;
	}








	
	
	
	
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
