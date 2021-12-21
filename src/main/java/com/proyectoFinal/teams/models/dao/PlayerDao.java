package com.proyectoFinal.teams.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;

public interface PlayerDao extends CrudRepository<Player,Long>{
	
	@Query("from Team")
	public List<Team> findAllTeams();
}
