package com.trabalho.biblioteca.repositorios;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.trabalho.biblioteca.entidades.Livro;

@SuppressWarnings("all")
public class LivroRepositorio {

	@Inject
	private EntityManager entityManager;
	
	public void inserir(Livro livro) {
		entityManager.persist(livro);
	}

	public List<Livro> todosLivros() {
		return entityManager.createQuery("select l from " + Livro.class.getSimpleName() + " l").getResultList();
	}
	
	public void remover(Livro livro) {
		entityManager.remove(entityManager.merge(livro));
	}

	public void removerPorId(Integer id) {
		Livro entity = entityManager.find(Livro.class, id);
		entityManager.remove(entity);	
	}

	public void atualizar(Livro livro) {
		entityManager.merge(livro);	
	}
	
	public Livro pesquisarPorID(Integer id) {
		return entityManager.find(Livro.class, id);
	}
	
}
