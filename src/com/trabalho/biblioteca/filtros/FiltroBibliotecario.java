package com.trabalho.biblioteca.filtros;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trabalho.biblioteca.entidades.Login;

@WebFilter(filterName = "FiltroBibliotecario",  urlPatterns = {
		"/paginas/livro.xhtml, paginas/emprestimo.xhtml" })
public class FiltroBibliotecario implements Filter {

	@Inject
	private Login session;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (session == null) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/paginas/principal.xhtml");
		} else if (session.getEmprestimo() == false && session.getLivro() == true && session.getPessoa() == false
				&& session.getAdmin() == false) {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

}