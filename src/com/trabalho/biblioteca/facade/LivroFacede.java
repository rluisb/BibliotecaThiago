package com.trabalho.biblioteca.facade;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.trabalho.biblioteca.entidades.Livro;
import com.trabalho.biblioteca.servicos.LivroServico;

@Path("/livros")
@Produces({ "application/json" })
public class LivroFacede {

	@Inject
	private LivroServico livroServico;

	@GET
	@Path("/")
	public List<Livro> listAll() {
		return livroServico.carregaTodosLivrosDoBanco();
	}

	@GET
	@Path("/{id}")
	public Livro umLivro(@PathParam("id") Integer id) {
		return livroServico.carregaUmLivro(id);
	}

}
