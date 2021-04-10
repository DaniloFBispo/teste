package br.com.develcode.teste.util.validacao;

public class ErrosSets {
	
	private String campo;
	private String erro;
	
	
	public ErrosSets(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}
	
	public String getErro() {
		return erro;
	}
	
	

}
