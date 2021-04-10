package br.com.develcode.teste.util.validacao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ErrosValidacao {
	
	@Autowired
	private MessageSource mensagem;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrosSets> execao(MethodArgumentNotValidException ex) {
		
		List<ErrosSets> errosSets = new ArrayList<ErrosSets>();
		List<FieldError> filds = ex.getBindingResult().getFieldErrors();
		
		filds.forEach(e ->{
			    String m = mensagem.getMessage(e, LocaleContextHolder.getLocale());
				ErrosSets erro = new ErrosSets(e.getField(),m );
				errosSets.add(erro);
		});
		
		return errosSets;
	}
}
