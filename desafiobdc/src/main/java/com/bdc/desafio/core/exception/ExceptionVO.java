package com.bdc.desafio.core.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigo = "";
	private String descricao = "";
	
}
