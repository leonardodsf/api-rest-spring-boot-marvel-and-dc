package br.com.trabalhofinalvargasflores.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.trabalhofinalvargasflores.entities.HeroisMarvel;
import br.com.trabalhofinalvargasflores.repositories.HeroisMarvelRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@Controller
@Api(description="Operações do serviço relacionadas a entidade Heróis da Marvel do banco de dados")
@RequestMapping("/herois-marvel")
public class HeroisMarvelController {

	@Autowired
	private HeroisMarvelRepository heroisMarvelRepository;
	
	@ApiOperation(value="Lista todos os heróis da marvel cadastrados")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<HeroisMarvel>> listHeroisMarvel() {
		List<HeroisMarvel> heroisMarvel = heroisMarvelRepository.findAll();
		ResponseEntity<List<HeroisMarvel>> response = new ResponseEntity<List<HeroisMarvel>>(heroisMarvel, new HttpHeaders(), HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="Pesquisa um herói da marvel pelo seu id")
	@GetMapping(path="/{id}", produces="application/json")
	public ResponseEntity<HeroisMarvel> getHeroisMarvel(@PathVariable("id") Long id) throws NotFoundException {
		if (heroisMarvelRepository.findById(id).isPresent() == false) {
			throw new NotFoundException("Não foi encontrado nenhum herói!!");
		} else {
			HeroisMarvel heroisMarvel = heroisMarvelRepository.findById(id).get();
			ResponseEntity<HeroisMarvel> response = new ResponseEntity<HeroisMarvel>(heroisMarvel, new HttpHeaders(), HttpStatus.OK);
			return response;
		}
	}
	
	@ApiOperation(value="Cadastra um herói da marvel")
	@PostMapping(produces = "application/json")
	public ResponseEntity<HeroisMarvel> addHeroisMarvel(@RequestBody HeroisMarvel heroisMarvel) {
		HeroisMarvel heroisMarvelSave = heroisMarvelRepository.save(heroisMarvel);
		ResponseEntity<HeroisMarvel> response = new ResponseEntity<HeroisMarvel>(heroisMarvelSave, new HttpHeaders(), HttpStatus.CREATED);
		return response;
	}
	
	@ApiOperation(value="Exclui um herói da marvel")
	@DeleteMapping(path="/{id}", produces="application/json")
	public ResponseEntity<HeroisMarvel> deleteHeroisMarvel(@PathVariable("id") Long id) {
		heroisMarvelRepository.deleteById(id);
		ResponseEntity<HeroisMarvel> response = new ResponseEntity<HeroisMarvel>(HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="Altera um herói da marvel")
	@PutMapping(produces="application/json")
	public ResponseEntity<HeroisMarvel> updateHeroisMarvel(@RequestBody HeroisMarvel heroisMarvel) throws NotFoundException {
		if (heroisMarvel.getId() == null) {
			throw new NotFoundException("Informe o id do herói da marvel que irá ser alterado!");
		} else if (heroisMarvelRepository.existsById(heroisMarvel.getId()) == false) {
			throw new NotFoundException("Não existe herói da marvel cadastrado com este id!");
		} else {
			HeroisMarvel heroisMarvelAltered = heroisMarvelRepository.save(heroisMarvel);
			ResponseEntity<HeroisMarvel> response = new ResponseEntity<HeroisMarvel>(heroisMarvelAltered, new HttpHeaders(), HttpStatus.OK);
			return response;
		}
	}
	
}
