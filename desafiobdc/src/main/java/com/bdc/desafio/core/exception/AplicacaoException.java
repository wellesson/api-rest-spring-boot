package com.bdc.desafio.core.exception;

public class AplicacaoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7362479057217023802L;

	public AplicacaoException(final String erroMessagem) {
		super(erroMessagem);
	}

	public AplicacaoException() {
		super();
	}

}
