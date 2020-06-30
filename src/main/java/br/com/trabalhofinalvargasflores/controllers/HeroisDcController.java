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
import br.com.trabalhofinalvargasflores.entities.HeroisDc;
import br.com.trabalhofinalvargasflores.repositories.HeroisDcRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;


@Controller
@Api(description="Operações do serviço relacionadas a entidade Heróis da DC Comics do banco de dados")
@RequestMapping("/herois-dc")
public class HeroisDcController {

	@Autowired
	private HeroisDcRepository heroisDcRepository;
	
	@ApiOperation(value="Lista todos os heróis da DC Comics cadastrados")
	@GetMapping(produces = "application/json")
	public ResponseEntity<List<HeroisDc>> listHeroisDC() {
		List<HeroisDc> heroisDC = heroisDcRepository.findAll();
		ResponseEntity<List<HeroisDc>> response = new ResponseEntity<List<HeroisDc>>(heroisDC, new HttpHeaders(), HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="Pesquisa um herói da DC Comics pelo seu id")
	@GetMapping(path="/{id}", produces="application/json")
	public ResponseEntity<HeroisDc> getHeroisDc(@PathVariable("id") Long id) throws NotFoundException {
		if (heroisDcRepository.findById(id).isPresent() == false) {
			throw new NotFoundException("Não foi encontrado nenhum herói da DC!!");
		} else {
			HeroisDc heroisDC = heroisDcRepository.findById(id).get();
			ResponseEntity<HeroisDc> response = new ResponseEntity<HeroisDc>(heroisDC, new HttpHeaders(), HttpStatus.OK);
			return response;
		}
	}
	
	@ApiOperation(value="Cadastra um herói da DC Comics")
	@PostMapping(produces = "application/json")
	public ResponseEntity<HeroisDc> addHeroisDc(@RequestBody HeroisDc heroisDC) {
		HeroisDc heroisDcSave = heroisDcRepository.save(heroisDC);
		ResponseEntity<HeroisDc> response = new ResponseEntity<HeroisDc>(heroisDcSave, new HttpHeaders(), HttpStatus.CREATED);
		return response;
	}
	
	@ApiOperation(value="Exclui um herói da DC Comics")
	@DeleteMapping(path="/{id}", produces="application/json")
	public ResponseEntity<HeroisDc> deleteHeroisDc(@PathVariable("id") Long id) {
		heroisDcRepository.deleteById(id);
		ResponseEntity<HeroisDc> response = new ResponseEntity<HeroisDc>(HttpStatus.OK);
		return response;
	}
	
	@ApiOperation(value="Altera um herói da DC Comics")
	@PutMapping(produces="application/json")
	public ResponseEntity<HeroisDc> updateHeroisDc(@RequestBody HeroisDc heroisDC) throws NotFoundException {
		if (heroisDC.getId() == null) {
			throw new NotFoundException("Informe o id do herói da dc que irá ser alterado!");
		} else if (heroisDcRepository.existsById(heroisDC.getId()) == false) {
			throw new NotFoundException("Não existe nenhum herói da dc cadastrado com este id!");
		} else {
			HeroisDc heroisDcAltered = heroisDcRepository.save(heroisDC);
			ResponseEntity<HeroisDc> response = new ResponseEntity<HeroisDc>(heroisDcAltered, new HttpHeaders(), HttpStatus.OK);
			return response;
		}
	}
	
}
