package com.proyectoFinal.teams.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.proyectoFinal.teams.models.entity.Usuario;

public interface UserDao extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
	

}
