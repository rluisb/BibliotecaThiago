package com.trabalho.biblioteca.managed.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Emprestimo;
import com.trabalho.biblioteca.servicos.EmprestimoServico;
import com.trabalho.biblioteca.util.Mensageiro;

@ManagedBean
@ViewScoped
public class EmprestimoManagedBean {

	private Emprestimo emprestimo;
	private List<Emprestimo> listaDeEmprestimoCadastrados;

	@Inject
	private EmprestimoServico servico;

	public EmprestimoManagedBean() {
	}

	public void salvar() {
		servico.salvar(getEmprestimo());	
		carregaListaDeEmprestimos();
		limpar();
	}

	public void devolver(Emprestimo emprestimo) {
		servico.devolver(emprestimo);
		Mensageiro.notificaInformacao("Parabéns!", "emprestimo devolvido com sucesso!");
		carregaListaDeEmprestimos();
		limpar();
	}

	public void limpar() {
		setEmprestimo(new Emprestimo());
	}

	private void carregaListaDeEmprestimos() {
		setListaDeEmprestimosCadastrados(servico.carregarTodosEmprestimosDoBanco());
	}

	public List<Emprestimo> getListaDeEmprestimosCadastrados() {
		if (listaDeEmprestimoCadastrados == null) {
			carregaListaDeEmprestimos();
		}
		return listaDeEmprestimoCadastrados;
	}

	public void setListaDeEmprestimosCadastrados(List<Emprestimo> listaDeEmprestimosCadastrados) {
		this.listaDeEmprestimoCadastrados = listaDeEmprestimosCadastrados;
	}

	public Emprestimo getEmprestimo() {
		if (emprestimo == null) {
			limpar();
		}
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

}
