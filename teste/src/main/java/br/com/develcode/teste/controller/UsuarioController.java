package br.com.develcode.teste.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.develcode.teste.controller.dto.UsuarioDTO;
import br.com.develcode.teste.entidades.Usuario;
import br.com.develcode.teste.repository.UsuarioRepository;
import br.com.develcode.teste.set.UsuarioSET;

@RestController
@RequestMapping("/teste")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public List<UsuarioDTO> lista(String nome) {

		if (nome == null) {
			List<Usuario> usuarios = usuarioRepository.findAll();
			return new UsuarioDTO().converter(usuarios);
		} else {
			List<Usuario> usuarios1 = usuarioRepository.findByNome(nome);
			return new UsuarioDTO().converter(usuarios1);
		}

	}

	@GetMapping("/{codigo}")
	public ResponseEntity<UsuarioDTO> unicoUsuario(@PathVariable Long codigo) {
		try {
			Usuario usuario = usuarioRepository.getOne(codigo);
			return ResponseEntity.ok(new UsuarioDTO(usuario));
		} catch (Exception e) {
			// e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<UsuarioDTO> cadastrarUsuario(@RequestBody @Valid UsuarioSET usuarioSet,
			UriComponentsBuilder uriBuilder) {

		Usuario usuario = usuarioSet.converter();
		usuarioRepository.save(usuario);

		URI uri = uriBuilder.path("/teste/{codigo}").buildAndExpand(usuario.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(new UsuarioDTO(usuario));

	}

	// Caso os campos de atualziação serem diferentes, ideal criar uma nova classe
	// só para isso
	@PutMapping("/{codigo}")
	@Transactional
	public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long codigo,
			@RequestBody @Valid UsuarioSET usuarioSet) {

		try {
			Usuario usuario = usuarioRepository.getOne(codigo);

			usuario.setDataNascimento(usuarioSet.getDataNascimento());
			usuario.setNome(usuarioSet.getNome());
			usuario.setFoto(usuarioSet.getFoto());

			return ResponseEntity.ok(new UsuarioDTO(usuario));

		} catch (Exception e) {
			// e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{codigo}")
	@Transactional
	public ResponseEntity<?> excluir(@PathVariable Long codigo) {

		try {
			usuarioRepository.deleteById(codigo);
			return ResponseEntity.ok().build();

		} catch (Exception e) {
			// e.printStackTrace();
			return ResponseEntity.notFound().build();
		}

	}
}
