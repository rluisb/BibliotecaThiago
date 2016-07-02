package com.trabalho.biblioteca.servicos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Emprestimo;
import com.trabalho.biblioteca.entidades.Livro;
import com.trabalho.biblioteca.repositorios.EmprestimoRepositorio;
import com.trabalho.biblioteca.repositorios.LivroRepositorio;
import com.trabalho.biblioteca.transacao.Transacional;
import com.trabalho.biblioteca.util.Mensageiro;

public class EmprestimoServico {
	@Inject
	private EmprestimoRepositorio repositorio;
	@Inject
	private LivroRepositorio repositorio1;

	@Transacional
	public void salvar(Emprestimo emprestimo) {
		if (emprestimo.getIdEmprestimo() == null) {
			if (emprestimo.getStatus() == null) {
				for (Livro confereEstoque : emprestimo.getLivros()) {
					if (confereEstoque.getEstoque() < 2) {
						Mensageiro.notificaInformacao("Desculpe", "Sem livros em estoque!");
						break;
					} else {

						confereEstoque.setEstoque(confereEstoque.getEstoque() - 1);

						emprestimo.setDataEntrada(LocalDateTime.now());
						emprestimo.setStatus("ALUGADO");
						repositorio.inserir(emprestimo);
						Mensageiro.notificaInformacao("Parabéns!", "Cadastro salvo com sucesso!");
					}

				}
			}

		}
	}

	@Transacional
	public List<Emprestimo> carregarTodosEmprestimosDoBanco() {
		return repositorio.todosEmprestimos();
	}

	@Transacional
	public void deletar(Emprestimo emprestimo) {
		repositorio.remover(emprestimo);

	}

	@Transacional
	public void devolver(Emprestimo emprestimo) {
		if (emprestimo.getStatus() != null) {
			emprestimo.setStatus(null);
			emprestimo.setDataSaida(LocalDateTime.now());
			Duration dur = Duration.between(emprestimo.getDataEntrada(), emprestimo.getDataSaida());
			for (Livro livroAtribuirEstoque : emprestimo.getLivros()) {
				livroAtribuirEstoque.setEstoque(livroAtribuirEstoque.getEstoque() + 1);
				repositorio1.atualizar(livroAtribuirEstoque);
			}

			if (dur.toDays() > 7) {
				emprestimo.setDiasAtrasados((int) dur.toDays() - 7);
				repositorio.devolver(emprestimo);
			} else {
				emprestimo.setDiasAtrasados(0);
				repositorio.devolver(emprestimo);
			}
		}
	}

}
