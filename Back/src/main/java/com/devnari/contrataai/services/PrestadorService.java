package com.devnari.contrataai.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.devnari.contrataai.model.Prestador;
import com.devnari.contrataai.model.Servico;
import com.devnari.contrataai.model.ServicoPrestado;
import com.devnari.contrataai.model.auxiliares.Experiencia;
import com.devnari.contrataai.persistencia.PrestadorDao;
import com.devnari.contrataai.persistencia.UserDao;
import com.devnari.contrataai.util.StringUtil;

@Service
public class PrestadorService {

	@Autowired
	PrestadorDao persistencia;
	@Autowired
	ServicoService servicoService;
	@Autowired
	ServicoPrestadoService servicoPrestadoService;
	@Autowired
	ExperienciaService experienciaService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	public Page<Prestador> buscarPorCategoria(String categoria, int page, int size) {
		return persistencia.findPrestadorByCategoriaDoServicoPrestado(categoria, PageRequest.of(page, size));
	}

	public Prestador buscarPorId(Long id) throws Exception {

		Prestador prestador = persistencia.findById(id).orElse(null);
		if (prestador == null) {
			throw new Exception("Prestador Não Encontrado!");
		}
		return prestador;
	}

	public Page<Prestador> buscarPorCpf(String cpf, int page, int size) {
		cpf = StringUtil.tratarStringNullEUndefinned(cpf);
		Page<Prestador> prestadores = persistencia.findByCpf(cpf, PageRequest.of(page, size));
		return prestadores;
	}

	public Page<Prestador> buscarPorNome(String nome, int page, int size) {
		nome = StringUtil.tratarStringNullEUndefinned(nome);
		Page<Prestador> prestadores = persistencia.findByNome(nome, PageRequest.of(page, size));
		return prestadores;
	}

	public Prestador buscarPorUsername(String nome) {
		nome = StringUtil.tratarStringNullEUndefinned(nome);
		Prestador prestador = persistencia.findByUsername(nome);
		return prestador;
	}

	public Prestador buscarPrestadorPorServicoPrestado(Long id) {
		Prestador prestador = persistencia.findPrestadorByServicoPrestado(id);
		return prestador;
	}

	public Prestador salvar(Prestador prestador) throws Exception {
		if (prestador == null) {
			throw new Exception("Prestador Não Informado!");
		}
//		prestador.getUsuario().setPassword(passwordEncoder.encode(prestador.getUsuario().getPassword()));
//		userDao.save(prestador.getUsuario());
		return persistencia.save(prestador);
	}

	public Prestador atualizar(Prestador prestador) throws Exception {
		if (prestador.getId() == null) {
			throw new Exception("Prestador Não Encontrado!");
		}
		return persistencia.save(prestador);
	}

	public String deletarPorId(Long id) throws Exception {
		if (!persistencia.existsById(id)) {
			throw new Exception("Não Encontrado!");
		}
		persistencia.deleteById(id);
		return "deletado Com Sucesso!";
	}

	public Prestador adicionarServico(Long idPrestador, Long idServicoPrestado) throws Exception {
		Prestador prestador = persistencia.findById(idPrestador).orElseThrow();
		ServicoPrestado servicoPrestado = servicoPrestadoService.buscarPorId(idServicoPrestado);
		prestador.getServicosPrestados().add(servicoPrestado);
		return prestador;
	}

	public ServicoPrestado criarServicoPrestado(Long idPrestador, Long idServico, Long idExperiencia) throws Exception {
		Servico servico = servicoService.buscarPorId(idServico);
		Prestador prestador = this.buscarPorId(idPrestador);
		Experiencia experiencia = experienciaService.buscarPorId(idExperiencia);
		ServicoPrestado servicoPrestado = new ServicoPrestado();
		servicoPrestado.setExperiencia(experiencia);
		servicoPrestado.setServico(servico);
		prestador.getServicosPrestados().add(servicoPrestado);
		persistencia.save(prestador);
		return servicoPrestadoService.salvar(servicoPrestado);
	}

}
