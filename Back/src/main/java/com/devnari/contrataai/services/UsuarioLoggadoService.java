package com.devnari.contrataai.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.devnari.contrataai.configurations.JwtTokenFilter;
import com.devnari.contrataai.model.Usuario;
import com.devnari.contrataai.model.UsuarioLoggado;
import com.devnari.contrataai.persistencia.UserDao;

@Service
public class UsuarioLoggadoService implements UserDetailsService {

	private static Long tempoExpiracaoDoToken = 60L * 60L * 1000L;
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> user = userDao.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("usuário não encontrado");
		}
		return getUsuarioLoggadoPorUsuario(user.get());
	}

	public UsuarioLoggado getUsuarioLoggadoPorUsuario(Usuario usuario) {
		return new UsuarioLoggado(usuario);
	}

	public String login(String username, String password) throws Exception {
		UsuarioLoggado usuario = (UsuarioLoggado) loadUserByUsername(username);
		if (passwordEncoder.matches(password, usuario.getPassword())) {
			String token = JWT.create().withSubject(usuario.getPassword()).withIssuer(usuario.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis() + tempoExpiracaoDoToken))
					.sign(Algorithm.HMAC512(JwtTokenFilter.SECRET.getBytes()));
			return token;
		} else {
			throw new Exception("Usuário ou senha inválidos!");
		}
	}

	public UsuarioLoggado findByToken(String token) throws Exception {
		Usuario usuario = new Usuario();
		DecodedJWT ret = JWT.decode(token);
		String username = ret.getIssuer();
		String password = ret.getSubject();

		UserDetails usuarioBanco = loadUserByUsername(username);
		if (!usuarioBanco.getPassword().equals(password)) {
			throw new Exception("Token Inválido!");
		}

		usuario.setUsername(ret.getIssuer());
		usuario.setPassword(ret.getSubject());
		UsuarioLoggado usuarioLoggado = new UsuarioLoggado(usuario);
		usuarioLoggado.setToken(token);
		if (usuarioLoggado.isAccountNonExpired()) {
			return usuarioLoggado;
		} else {
			throw new Exception("Login expirado!");
		}

	}

	public Usuario save(Usuario usuario) {
		usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return userDao.save(usuario);
	}

	public List<Usuario> findAll() {
		return userDao.findAll();
	}

}
