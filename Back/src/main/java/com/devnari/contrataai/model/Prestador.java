package com.devnari.contrataai.model;

import java.util.List;

import com.devnari.contrataai.enumerations.DiasSemana;
import com.devnari.contrataai.enumerations.PeriodosDia;
import com.devnari.contrataai.model.auxiliares.Contato;
import com.devnari.contrataai.model.auxiliares.Disponibilidade;
import com.devnari.contrataai.model.auxiliares.Endereco;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Prestador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;
	private String email;

	@ManyToOne
	@JoinColumn(name = "contato_id")
	private Contato contato;
	private String CPF;

	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

// link da foto
	private String foto;

	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<ServicoPrestado> servicoPrestados;

// link do portfólio
	private String portfolio;
	@ManyToOne
	@JoinColumn(name = "disponibilidade_id")
	private Disponibilidade disponibilidades;
	private String descricaoAdicional;

}
