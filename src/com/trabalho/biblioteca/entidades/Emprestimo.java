package com.trabalho.biblioteca.entidades;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.trabalho.biblioteca.conversores.LocalDateTimeConverter;

@Entity
public class Emprestimo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer idEmprestimo;

	@JoinColumn(name = "idPessoa", nullable = false)
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Pessoa pessoa;

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "idLivro", nullable = false)
	private List<Livro> livros;

	@JoinColumn(name = "idFuncionario", nullable = false)
	@ManyToOne(cascade = CascadeType.REFRESH)
	private Funcionario funcionario;

	@Column
	private String status;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column
	private LocalDateTime dataSaida;

	@Convert(converter = LocalDateTimeConverter.class)
	@Column
	private LocalDateTime dataEntrada;
	@Column
	private Integer diasAtrasados;

	public Integer getIdEmprestimo() {
		return idEmprestimo;
	}

	public void setIdEmprestimo(Integer idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Integer getDiasAtrasados() {
		return diasAtrasados;
	}

	public void setDiasAtrasados(Integer diasAtrasados) {
		this.diasAtrasados = diasAtrasados;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataEntrada == null) ? 0 : dataEntrada.hashCode());
		result = prime * result + ((dataSaida == null) ? 0 : dataSaida.hashCode());
		result = prime * result + ((diasAtrasados == null) ? 0 : diasAtrasados.hashCode());
		result = prime * result + ((funcionario == null) ? 0 : funcionario.hashCode());
		result = prime * result + ((idEmprestimo == null) ? 0 : idEmprestimo.hashCode());
		result = prime * result + ((livros == null) ? 0 : livros.hashCode());
		result = prime * result + ((pessoa == null) ? 0 : pessoa.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emprestimo other = (Emprestimo) obj;
		if (dataEntrada == null) {
			if (other.dataEntrada != null)
				return false;
		} else if (!dataEntrada.equals(other.dataEntrada))
			return false;
		if (dataSaida == null) {
			if (other.dataSaida != null)
				return false;
		} else if (!dataSaida.equals(other.dataSaida))
			return false;
		if (diasAtrasados == null) {
			if (other.diasAtrasados != null)
				return false;
		} else if (!diasAtrasados.equals(other.diasAtrasados))
			return false;
		if (funcionario == null) {
			if (other.funcionario != null)
				return false;
		} else if (!funcionario.equals(other.funcionario))
			return false;
		if (idEmprestimo == null) {
			if (other.idEmprestimo != null)
				return false;
		} else if (!idEmprestimo.equals(other.idEmprestimo))
			return false;
		if (livros == null) {
			if (other.livros != null)
				return false;
		} else if (!livros.equals(other.livros))
			return false;
		if (pessoa == null) {
			if (other.pessoa != null)
				return false;
		} else if (!pessoa.equals(other.pessoa))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
