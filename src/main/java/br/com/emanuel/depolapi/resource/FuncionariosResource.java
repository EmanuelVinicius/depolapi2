package br.com.emanuel.depolapi.resource;

import java.net.URI;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.emanuel.depolapi.domain.Funcionario;
import br.com.emanuel.depolapi.service.FuncionariosService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionariosResource {
	
	private FuncionariosService funcionariosService;

	public FuncionariosResource(FuncionariosService funcionariosService) {
		this.funcionariosService = funcionariosService;
	}

	@GetMapping
	public List<Funcionario> listar() {
		return funcionariosService.listar();
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Validated @RequestBody Funcionario funcionario) throws Exception {
		funcionariosService.salvar(funcionario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(funcionario.getIdFuncionario()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public void alterar(@Validated @RequestBody Funcionario funcionario) {
		funcionariosService.atualizar(funcionario);
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable("id") Long idFuncionario) throws Exception {
		funcionariosService.deletarPorId(idFuncionario);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable("id") Long idFuncionario) throws Exception {
		return ResponseEntity.ok(funcionariosService.buscarPorId(idFuncionario));
	}


}
