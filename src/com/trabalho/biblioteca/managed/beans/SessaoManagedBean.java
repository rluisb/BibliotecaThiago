package com.trabalho.biblioteca.managed.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.trabalho.biblioteca.entidades.Login;
import com.trabalho.biblioteca.servicos.LoginServico;

@ManagedBean(eager = true)
@SessionScoped

public class SessaoManagedBean {

	private String usuario;
	private String senha;

	@Inject
	private Login login;
	@Inject
	private LoginServico servico;

	public SessaoManagedBean() {
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Login getLogin() {
		return login;
	}

	public LoginServico getServico() {
		return servico;
	}

	public String entrar() {
		List<Login> listaLoginsDoBanco = servico.carregaTodosLoginsDoBanco();
		for (Login loginDoBanco : listaLoginsDoBanco) {
			if (loginDoBanco.getUsuario().equals(usuario) && loginDoBanco.getSenha().equals(senha)) {
				this.login = loginDoBanco;
				login.setLogado(true);
				if (login.getAdmin() == true) {
					return "funcionario.xhtml?faces-redirect=true";
				}
				if (login.getPessoa()== true) {
					return "pessoa.xhtml?faces-redirect=true";
				}
				if (login.getEmprestimo() == true) {
					return "emprestimo.xhtml?faces-redirect=true";
				} else {
					return "livro.xhtml?faces-redirect=true";
				}
			}
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário e/ou senha inválidos.", null));
		return "principal.xhtml?faces-redirect=true";
	}

	public String sair() {
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.getExternalContext().invalidateSession();
		return "principal.xhtml?faces-redirect=true";
	}

}