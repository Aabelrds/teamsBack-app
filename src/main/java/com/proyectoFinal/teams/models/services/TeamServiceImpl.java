package com.proyectoFinal.teams.models.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.proyectoFinal.teams.models.dao.TeamDao;
import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private TeamDao teamDao;
	
	@Override
	@Transactional(readOnly =true)
	public List<Team> findAll() {

		return (List<Team>) teamDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Team findById(Long id) {

		return teamDao.findById(id).orElse(null);
	}

	@Override
	public Team save(Team team) {
		
		return teamDao.save(team);
	}

	@Override
	public void delete(Long id) {
		
		teamDao.deleteById(id);		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Player> findAllPlayers() {
		
		return teamDao.findAllPlayers()	;
	}

}
