package com.proyectoFinal.teams.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;

public interface TeamDao extends CrudRepository<Team,Long>{
	
	@Query("from Player")
	public List<Player> findAllPlayers();

}
