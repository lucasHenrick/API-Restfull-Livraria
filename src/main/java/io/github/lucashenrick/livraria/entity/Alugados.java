package io.github.lucashenrick.livraria.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "aluguel")
public class Alugados {
	@Id
    @GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
    private long id;
	@Column(nullable = false)
    private String nomeCliente;
	@Column(nullable = false)
    private String nomeLivro;
	@Column(nullable = false)
    private Double valorLivro;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getNomeLivro() {
		return nomeLivro;
	}
	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}
	public Double getValorLivro() {
		return valorLivro;
	}
	public void setValorLivro(double valorLivro) {
		this.valorLivro = valorLivro;
	}
	
}
