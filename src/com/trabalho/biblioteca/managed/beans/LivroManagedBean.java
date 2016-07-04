package com.trabalho.biblioteca.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Livro;
import com.trabalho.biblioteca.servicos.LivroServico;

@ManagedBean
@ViewScoped
public class LivroManagedBean {

	private Livro livro;
	private List<Livro> listaDeLivrosCadastrados;

	@Inject
	private LivroServico servico;

	public LivroManagedBean() {
	}

	public void salvar() {
		servico.salvar(getLivro());
		carregaListaDeLivros();
		limpar();
	}

	public void deletar(Livro livro) {
		servico.deletar(livro);

		carregaListaDeLivros();
		limpar();
	}

	public void limpar() {
		setLivro(new Livro());
	}

	private void carregaListaDeLivros() {
		setListaDeLivrosCadastrados(servico.carregaTodosLivrosDoBanco());
	}

	public List<Livro> getListaDeLivrosCadastrados() {
		if (listaDeLivrosCadastrados == null) {
			carregaListaDeLivros();
		}
		return listaDeLivrosCadastrados;
	}

	public void setListaDeLivrosCadastrados(List<Livro> listaDeLivrosCadastrados) {
		this.listaDeLivrosCadastrados = listaDeLivrosCadastrados;
	}

	public Livro getLivro() {
		if (livro == null) {
			limpar();
		}
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

}