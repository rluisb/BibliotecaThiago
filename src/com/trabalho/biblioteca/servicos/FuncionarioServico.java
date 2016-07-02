package com.trabalho.biblioteca.servicos;

import java.util.List;

import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Funcionario;
import com.trabalho.biblioteca.repositorios.FuncionarioRepositorio;
import com.trabalho.biblioteca.transacao.Transacional;

public class FuncionarioServico {

	@Inject
	private FuncionarioRepositorio repositorio;

	@Transacional
	public void salvar(Funcionario funcionario) {
		if (funcionario.getIdFuncionario() == null) {
			repositorio.inserir(funcionario);
		} else {
			repositorio.atualizar(funcionario);
		}
	}

	@Transacional
	public List<Funcionario> carregaTodosFuncionariosDoBanco() {
		return repositorio.todosFuncionarios();
	}

	@Transacional
	public void deletar(Funcionario funcionario) {
		repositorio.remover(funcionario);
		;
	}

}
