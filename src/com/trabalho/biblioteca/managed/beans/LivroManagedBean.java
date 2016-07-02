package com.trabalho.biblioteca.managed.beans;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import org.primefaces.event.FileUploadEvent;

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

	public void uploadFoto(FileUploadEvent event) {
		byte[] foto = event.getFile().getContents();
		this.livro.setFoto(foto);
	}

	public String getFoto(byte[] fotoArray) throws Exception {
		String localSave = ("C:\\dev\\Workspaces\\Workspace-Eclipse-Mars2\\Workspace-Eclipse-Pedro\\HackathonV2Pedro\\WebContent\\resources\\fotosDoBanco\\");
		File file = new File(localSave + fotoArray + ".jpg");
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fotoArray);
			FileDescriptor fd = outputStream.getFD();
			outputStream.flush();
			fd.sync();
			outputStream.close();
		} catch (Exception e) {
			throw new Exception("Erro ao converter os bytes recebidos para imagem");

		}
		return file.getPath();
	}

}