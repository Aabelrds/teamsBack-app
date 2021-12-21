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
import com.proyectoFinal.teams.models.services.PlayerService;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PlayerRestController {
	
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/players")
	public List<Player> index() {
		
		return playerService.findAll();	
		}
	
	@GetMapping("/players/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Player player = null;
		Map<String, Object> response = new HashMap<>();

		try {
			player = playerService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar consulta en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (player == null) {
			response.put("mensaje",
					"El jugador con ID : ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}
	
	@PostMapping("/players")
	public ResponseEntity<?> create(@RequestBody Player player) {

		Player playerNew = null;
		Map<String, Object> response = new HashMap<>();

		try {
			
			playerNew = playerService.save(player);
			
		} catch (DataAccessException e) {
			
			response.put("message", "Error al realizar insert en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
		response.put("mensaje", "Team creado con éxito");
		response.put("player", playerNew);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	
	@PutMapping("/players/{id}")
	public ResponseEntity<?> update(@RequestBody Player player, @PathVariable Long id) {

		Player actualPlayer = playerService.findById(id);

		Map<String, Object> response = new HashMap<>();
		Player playerUpdated = null;

		if (actualPlayer == null) {
			response.put("mensaje", "Error: No se puede editar, el player ID : "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			actualPlayer.setName(player.getName());
			actualPlayer.setSurName(player.getSurName());
			actualPlayer.setCountry(player.getCountry());
			actualPlayer.setCity(player.getCity());
			actualPlayer.setTeam(player.getTeam());
						

			playerUpdated = playerService.save(actualPlayer);

		} catch (DataAccessException e) {
			response.put("message", "Error al actualizar al player en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "Team actualizado con éxito");
		response.put("player", playerUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	
	@DeleteMapping("players/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			playerService.delete(id);
			
		} catch (DataAccessException e) {
			
			response.put("message", "Error al eliminar al player en base de datos");
			response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El player se ha eliminado");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	@PostMapping("players/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap<>();

		Player player = playerService.findById(id);
		
		if (!archivo.isEmpty()) {
			String nombreArchivo = UUID.randomUUID().toString() + "-" + archivo.getOriginalFilename().replace(" ", "");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();

			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);

			} catch (IOException e) {

				response.put("message", "Error al subir la imagen del player");
				response.put("error", e.getMessage().concat(" : ").concat(e.getCause().getMessage()));
				
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

			}

			String nombreFotoAnterior = player.getImage();

			if (nombreFotoAnterior != null) {

				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoAnterior = rutaFotoAnterior.toFile();
				archivoAnterior.delete();
				System.out.println(archivoAnterior + " Archivo eliminado");

			}
			player.setImage(nombreArchivo);
			playerService.save(player);

			response.put("mensaje", "Imagen guardada correctamente " + nombreArchivo);
			response.put("player", player);

		}
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	

	
	@GetMapping("players/teams")
	public List<Team> teamList(){
		
		return playerService.findAllTeams();
		
	}
	
}
