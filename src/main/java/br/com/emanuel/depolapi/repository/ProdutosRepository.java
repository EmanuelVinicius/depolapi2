package br.com.emanuel.depolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuel.depolapi.domain.Produto;

public interface ProdutosRepository extends JpaRepository<Produto, Long> {

	public Produto findByNome(String nome);
}
