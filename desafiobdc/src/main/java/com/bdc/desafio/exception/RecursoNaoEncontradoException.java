package com.bdc.desafio.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 8387147298218246196L;

	public RecursoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RecursoNaoEncontradoException() {
		super();
	}

}
