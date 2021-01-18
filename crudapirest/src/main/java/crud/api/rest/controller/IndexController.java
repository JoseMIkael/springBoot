package crud.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import crud.api.rest.model.Alunos;
import crud.api.rest.repository.AlunosRepository;

@RestController
@RequestMapping(value = "/usuario")
public class IndexController {
	@Autowired
	private AlunosRepository alunosRepository;
	
	//Gravando aluno.
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Alunos> cadastrar(@RequestBody Alunos alunos){
		Alunos alunosSalvo = alunosRepository.save(alunos);
		return new ResponseEntity<Alunos>(alunosSalvo, HttpStatus.OK);
		
	}
	//Atualizando o cadastro.
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Alunos> atualizar(@RequestBody Alunos alunos){
		Alunos alunosSalvo = alunosRepository.save(alunos);
		return new ResponseEntity<Alunos>(alunosSalvo, HttpStatus.OK);
		
	}
	//Deletando os alunos
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String delete (@PathVariable("id") Long id) {
		alunosRepository.deleteById(id);
		return "Dados apagados";
	}		
	//Buscando um aluno existente pelo o Id.
	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Alunos> init(@PathVariable(value = "id") Long id) {
		
		Optional<Alunos> alunos = alunosRepository.findById(id);
		
		return new ResponseEntity(alunos.get(), HttpStatus.OK);
	}
	//Buscando todos os alunos existente.
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Alunos>> alunos(){
		List<Alunos> list = (List<Alunos>) alunosRepository.findAll();	
		return new ResponseEntity<List<Alunos>>(list, HttpStatus.OK);
	}
	//Buscando os dados do aluno pelo o email.
	@GetMapping(value ="/{email}", produces = "application/text")
	public List<Alunos> findPersonByEmail(@RequestParam("email") String email){
		return alunosRepository.findByEmailContains(email).stream().map(Alunos::converter).collect(Collectors.toList());
		
	}
	
}
