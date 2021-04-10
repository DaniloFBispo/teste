package br.com.develcode.teste.controller.dto;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import br.com.develcode.teste.entidades.Usuario;

public class UsuarioDTO {
	
	private Long codigo;
	private String nome;
	private Date dataNascimento;
	
	
	public UsuarioDTO(Usuario usuario) {
		this.codigo = usuario.getCodigo();
		this.nome = usuario.getNome();
		this.dataNascimento = usuario.getDataNascimento();
			
	}
	
	public UsuarioDTO() {
					
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public List<UsuarioDTO> converter(List<Usuario> usuarios){
	    if(usuarios!=null && usuarios.isEmpty()) {
	    	return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
		}else {
			return null;
		
		}
	}

}
