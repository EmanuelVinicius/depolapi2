package br.com.emanuel.depolapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.emanuel.depolapi.domain.Produto;
import br.com.emanuel.depolapi.repository.ProdutosRepository;

@Service
public class ProdutosService {
	@Autowired
	private ProdutosRepository produtosRepository;

	public Produto salvar(@Validated Produto produto) throws Exception {
		Produto p = produtosRepository.findByNome(produto.getNome());
		
		if(p != null) {
			throw new Exception("Este produto informado já está cadastrado");
		}
		
		Produto produtoCriado = produtosRepository.save(produto);
		return produtoCriado;
	}

	public Produto atualizar(@Validated Produto produto) {
		Produto funcionarioAtualizado = produtosRepository.save(produto);
		return funcionarioAtualizado;
	}

	public void deletarPorId(Long id) throws Exception {
		Produto funcionariosExclusao = produtosRepository.findById(id)
				.orElseThrow(() -> new Exception("Nenhum registro encontrado com o ID"));

		produtosRepository.deleteById(id);
	}

	public Produto buscarPorId(Long id) throws Exception {
		return produtosRepository.findById(id)
				.orElseThrow(() -> new Exception("Nenhum registro encontrado com o ID"));
	}

	public List<Produto> listar() {
		return produtosRepository.findAll();
	}
	
	
	
}
