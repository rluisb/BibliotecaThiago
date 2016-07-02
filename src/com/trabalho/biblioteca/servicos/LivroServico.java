package com.trabalho.biblioteca.servicos;

import java.util.List;

import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Livro;
import com.trabalho.biblioteca.repositorios.LivroRepositorio;
import com.trabalho.biblioteca.transacao.Transacional;
import com.trabalho.biblioteca.util.Mensageiro;

public class LivroServico {

	@Inject
	private LivroRepositorio repositorio;

	@Transacional
	public void salvar(Livro livro) {
		if (livro.getIdLivro() == null) {
			repositorio.inserir(livro);
			Mensageiro.notificaInformacao("Parabéns!", "Livro salvo com sucesso!");
		} else {
			repositorio.atualizar(livro);
			Mensageiro.notificaInformacao("Parabéns!", "Livro salvo com sucesso!");
		}
	}

	@Transacional
	public List<Livro> carregaTodosLivrosDoBanco() {
		return repositorio.todosLivros();
	}

	@Transacional
	public Livro carregaUmLivro(Integer id) {
		return repositorio.pesquisarPorID(id);
	}
	
	@Transacional
	public void deletar(Livro livro) {
		repositorio.remover(livro);
		;
		Mensageiro.notificaInformacao("Parabéns!", "Livro deletado com sucesso!");
	}

}
