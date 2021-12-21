package com.proyectoFinal.teams.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.proyectoFinal.teams.models.entity.Player;
import com.proyectoFinal.teams.models.entity.Team;
import com.proyectoFinal.teams.models.services.TeamService;



@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TeamRestController {

	
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/teams")
	public List<Team> index() {
		
		return teamService.findAll();	
		}
	
	@GetMapping("/teams/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Team team = null;
		Map<String, Object> response = new HashMap<>();

		try {
			team = teamService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (team == null) {
			response.put("mensaje",
					"El team con ID : ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Team>(team, HttpStatus.OK);
	}
	
	@PostMapping("/teams")
	public ResponseEntity<?> create(@RequestBody Team team) {

		Team teamNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			
			teamNew = teamService.save(team);
			
		} catch (DataAccessException e) {
			
			response.put("message", "Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		response.put("mensaje", "Team creado con éxito");
		response.put("cliente", teamNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	
	@PutMapping("/teams/{id}")
	public ResponseEntity<?> update(@RequestBody Team team, @PathVariable Long id) {

		Team actualTeam = teamService.findById(id);

		Map<String, Object> response = new HashMap<>();
		Team teamUpdated = null;

		if (actualTeam == null) {
			response.put("mensaje", "Error: No se puede editar, el team ID : "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			actualTeam.setName(team.getName());
			actualTeam.setCity(team.getCity());
			actualTeam.setCountry(team.getCountry());
			actualTeam.setHimno(team.getHimno());
			actualTeam.setUclTrophies(team.getUclTrophies());

			if (team.getCreatedAt() == null) {

				actualTeam.setCreatedAt(actualTeam.getCreatedAt());

			} else {

				actualTeam.setCreatedAt(actualTeam.getCreatedAt());
			}

			teamUpdated = teamService.save(actualTeam);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar al team en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Team actualizado con éxito");
		response.put("cliente", teamUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@DeleteMapping("teams/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			teamService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("message", "Error al eliminar al team en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El team se ha eliminado");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("teams/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Team team = teamService.findById(id);

		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "-" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);

			} catch (IOException e) {

				response.put("message", "Error al subir la imagen del team");
				response.put("error", e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}

			String nombreFotoAnterior = team.getImage();

			if (nombreFotoAnterior != null) {

				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoAnterior = rutaFotoAnterior.toFile();
				archivoAnterior.delete();
				System.out.println(archivoAnterior + " Archivo eliminado");

			}
			team.setImage(nombreArchivo);
			teamService.save(team);

			response.put("mensaje", "Imagen guardada correctamente " + nombreArchivo);
			response.put("team", team);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();

		Resource recurso = null;

		try {
			recurso = new UrlResource(rutaArchivo.toUri());

		} catch (MalformedURLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!recurso.exists()) {
			throw new RuntimeException("Error no se puede cargar la imagen " + nombreFoto);

		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	@GetMapping("teams/players")
	public List<Player> playersList(){
		
		return teamService.findAllPlayers();

	}
	
}
