package com.proyectoFinal.teams.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.proyectoFinal.teams.models.dao.PlayerDao;
import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDao playerDao;
	
	@Override
	@Transactional(readOnly =true)
	public List<Player> findAll() {

		return (List<Player>) playerDao.findAll();
	}

	@Override
	@Transactional(readOnly =true)
	public Player findById(Long id) {

		return playerDao.findById(id).orElse(null);
	}

	@Override
	public Player save(Player player) {

		return playerDao.save(player);
	}

	@Override
	public void delete(Long id) {
		
		playerDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly =true)
	public List<Team> findAllTeams() {
		
		return playerDao.findAllTeams();
	}

}
