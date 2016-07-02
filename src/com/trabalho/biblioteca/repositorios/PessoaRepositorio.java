package com.trabalho.biblioteca.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.trabalho.biblioteca.entidades.Pessoa;

@SuppressWarnings("all")
public class PessoaRepositorio {

	@Inject
	private EntityManager entityManager;

	public void inserir(Pessoa pessoa) {
		entityManager.persist(pessoa);
	}

	public List<Pessoa> todasPessoas() {
		return entityManager.createQuery("select l from " + Pessoa.class.getSimpleName() + " l").getResultList();
	}

	public void remover(Pessoa pessoa) {
		entityManager.remove(entityManager.merge(pessoa));
	}

	public void removerPorId(Integer id) {
		Pessoa entity = entityManager.find(Pessoa.class, id);
		entityManager.remove(entity);
	}

	public void atualizar(Pessoa pessoa) {
		entityManager.merge(pessoa);
	}

	public Pessoa pesquisarPorID(Integer id) {
		return entityManager.find(Pessoa.class, id);
	}

}