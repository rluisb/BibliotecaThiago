package com.trabalho.biblioteca.servicos;

import java.util.List;

import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Login;
import com.trabalho.biblioteca.repositorios.LoginRepositorio;
import com.trabalho.biblioteca.transacao.Transacional;

public class LoginServico {
	@Inject
	private LoginRepositorio repositorio;

	@Transacional
	public void salvar(Login login) {
		if (login.getIdLogin() == null ) {
			repositorio.inserir(login);
		} else {
			repositorio.atualizar(login);
		}
	}

	@Transacional
	public List<Login> carregaTodosLoginsDoBanco() {
		return repositorio.todosLogins();
	}

	@Transacional
	public void deletar(Login login) {
		repositorio.remover(login);
		;
	}
}
