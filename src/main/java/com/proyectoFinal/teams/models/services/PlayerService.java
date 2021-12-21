package com.proyectoFinal.teams.models.services;

import java.util.List;

import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;

public interface PlayerService {
	
	public List<Player> findAll();
	
	public Player findById(Long id);
	
	public Player save(Player player);
	
	public void delete(Long id);
	
	public List<Team>findAllTeams();

}
