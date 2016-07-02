package com.trabalho.biblioteca.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.trabalho.biblioteca.entidades.Emprestimo;

@SuppressWarnings("all")
public class EmprestimoRepositorio {

	@Inject
	private EntityManager entityManager;

	public void inserir(Emprestimo emprestimo) {
		entityManager.persist(emprestimo);
	}

	public List<Emprestimo> todosEmprestimos() {
		return entityManager.createQuery("select l from " + Emprestimo.class.getSimpleName() + " l").getResultList();
	}

	public void remover(Emprestimo emprestimo) {
		entityManager.remove(entityManager.merge(emprestimo));
	}
	public void devolver(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);
	}

	public void removerPorId(Integer id) {
		Emprestimo entity = entityManager.find(Emprestimo.class, id);
		entityManager.remove(entity);
	}

	public void atualizar(Emprestimo emprestimo) {
		entityManager.merge(emprestimo);
	}

	public Emprestimo pesquisarPorID(Integer id) {
		return entityManager.find(Emprestimo.class, id);
	}

}
