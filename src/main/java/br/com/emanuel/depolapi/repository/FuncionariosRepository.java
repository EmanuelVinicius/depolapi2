package br.com.emanuel.depolapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emanuel.depolapi.domain.Funcionario;

public interface FuncionariosRepository extends JpaRepository <Funcionario,Long> {
	
	public Funcionario findByEmail(String email);
}
