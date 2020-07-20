package com.br.desafio.exception;

public class RegistroJaExistenteException extends RuntimeException {

	private static final long serialVersionUID = 8387147298218246196L;

	public RegistroJaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public RegistroJaExistenteException() {
		super();
	}

}
