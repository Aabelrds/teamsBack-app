package com.proyectoFinal.teams.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "players")
public class Player  implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String surName;
	
	@Column(nullable=false)
	private String city;
	
	@Column(nullable=false)
	private String country;
	
	private String image;
	
	@NotNull(message = "No puede estar vacio")
	@ManyToOne
	@JoinColumn(name="teams_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Team team;
	
	
	
	
	public String getImage() {
		return image;
	}






	public void setImage(String image) {
		this.image = image;
	}






	public Team getTeam() {
		return team;
	}






	public void setTeam(Team team) {
		this.team = team;
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






	public String getSurName() {
		return surName;
	}






	public void setSurName(String surName) {
		this.surName = surName;
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



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
