package com.bdc.desafio.core.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.br.desafio.exception.RecursoNaoEncontradoException;
import com.br.desafio.exception.RegistroJaExistenteException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AplicacaoException.class, RecursoNaoEncontradoException.class, RegistroJaExistenteException.class, org.hibernate.PropertyValueException.class })
	public ResponseEntity<Object> handleException(final Throwable ex, final WebRequest request) {

		log.error(ExceptionUtils.getMessage(ex), ex);

		if (ex instanceof AplicacaoException) {

			final ExceptionVO exceptionVO = new ExceptionVO("400", ex.getMessage());

			return new ResponseEntity<>(exceptionVO, HttpStatus.BAD_REQUEST);

		} else if (ex instanceof RecursoNaoEncontradoException) {

			final ExceptionVO exceptionVO = new ExceptionVO("404", "Registro não encontrado!");

			return new ResponseEntity<>(exceptionVO, HttpStatus.NOT_FOUND);

		} else if (ex instanceof RegistroJaExistenteException) {

			final ExceptionVO exceptionVO = new ExceptionVO("400", "Registro já cadastrado!");

			return new ResponseEntity<>(exceptionVO, HttpStatus.BAD_REQUEST);

		} else if (ex instanceof RegistroJaExistenteException) {

			final ExceptionVO exceptionVO = new ExceptionVO("400", "Campo obrigatório não informado!");

			return new ResponseEntity<>(exceptionVO, HttpStatus.BAD_REQUEST);

		}  else {

			final ExceptionVO exceptionVO = new ExceptionVO("Erro interno do servidor!", "500");

			return new ResponseEntity<>(exceptionVO, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

}
