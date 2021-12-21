package com.proyectoFinal.teams.models.services;

import java.util.List;

import com.proyectoFinal.teams.models.entity.Team;
import com.proyectoFinal.teams.models.entity.Player;


public interface TeamService{
	
	public List<Team> findAll();
	
	public Team findById(Long id);
	
	public Team save(Team team);
	
	public void delete(Long id);
	
	public List<Player>findAllPlayers();

}
