package br.com.develcode.teste.set;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.develcode.teste.entidades.Usuario;


public class UsuarioSET {
	@NotNull
	@NotEmpty
	@Length(max = 300)
	private String nome;

	private Date dataNascimento;

	private byte[] foto;
	
	

	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Date getDataNascimento() {
		return dataNascimento;
	}



	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}



	public byte[] getFoto() {
		return foto;
	}



	public void setFoto(byte[] foto) {
		this.foto = foto;
	}



	public Usuario converter() {
		return new Usuario( nome, dataNascimento, foto);
	}
	
}
