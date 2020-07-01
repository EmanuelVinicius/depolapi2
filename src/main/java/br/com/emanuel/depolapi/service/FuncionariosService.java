package br.com.emanuel.depolapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.emanuel.depolapi.domain.Funcionario;
import br.com.emanuel.depolapi.repository.FuncionariosRepository;

@Service
public class FuncionariosService {
	@Autowired
	private FuncionariosRepository funcionariosRepository;

	public Funcionario salvar(@Validated Funcionario funcionario) throws Exception {
		Funcionario f = funcionariosRepository.findByEmail(funcionario.getEmail());
		
		if(f != null) {
			throw new Exception("o email informado já está cadastrado");
		}
		
		Funcionario funcionarioCriado = funcionariosRepository.save(funcionario);
		return funcionarioCriado;
	}

	public Funcionario atualizar(@Validated Funcionario funcionario) {
		Funcionario funcionarioAtualizado = funcionariosRepository.save(funcionario);
		return funcionarioAtualizado;
	}

	public void deletarPorId(Long id) throws Exception {
		Funcionario funcionariosExclusao = funcionariosRepository.findById(id)
				.orElseThrow(() -> new Exception("Nenhum registro encontrado com o ID"));

		funcionariosRepository.deleteById(id);
	}

	public Funcionario buscarPorId(Long id) throws Exception {
		return funcionariosRepository.findById(id)
				.orElseThrow(() -> new Exception("Nenhum registro encontrado com o ID"));
	}

	public List<Funcionario> listar() {
		return funcionariosRepository.findAll();
	}
	
	
}
