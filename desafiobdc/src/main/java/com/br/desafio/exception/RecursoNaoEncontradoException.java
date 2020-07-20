package com.br.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8387147298218246196L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoNaoEncontradoException() {
		super();
	}

}
