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

import br.com.emanuel.depolapi.domain.Produto;

import br.com.emanuel.depolapi.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
public class ProdutosResource {

	private ProdutosService produtosService;

	public ProdutosResource(ProdutosService produtosService) {
		this.produtosService = produtosService;
	}

	@GetMapping
	public List<Produto> listar() {
		return produtosService.listar();
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@Validated @RequestBody Produto produto) throws Exception {
		produtosService.salvar(produto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getIdProduto())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping
	public void alterar(@Validated @RequestBody Produto produto) {
		produtosService.atualizar(produto);
	}

	@DeleteMapping(value = "/{id}")
	public void deletar(@PathVariable("id") Long idProduto) throws Exception {
		produtosService.deletarPorId(idProduto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Produto> buscarPorId(@PathVariable("id") Long idProduto) throws Exception {
		return ResponseEntity.ok(produtosService.buscarPorId(idProduto));
	}
}
