package com.bmais.gestao.restapi.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Objects;

@RestControllerAdvice
public class BMaisExceptionHandler{

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public HashMap<String, String> sqlExceptionHandler(Exception exception){
		HashMap<String, String> error = new HashMap<>();
		if(exception instanceof DataIntegrityViolationException){
			if(exception.getCause() instanceof ConstraintViolationException){
				ConstraintViolationException constrainError = (ConstraintViolationException) exception.getCause();
				if(constrainError.getSQLException() != null){
					SQLException sqlError = constrainError.getSQLException();
					if(Objects.equals(sqlError.getSQLState(), "23503")) error.put("Erros", "Não é possivel apagar, registro em uso");
				}
			}else {
				error.put("Erros", "Ocorreu um erro no banco de dados, contate a equipe técnica do sistema");
			}
		}else if(exception instanceof CadastroJaExistente
				|| exception instanceof IncorrectData
				|| exception instanceof MandatoryAttribute
				|| exception instanceof ObjectAlreadyInBase
				|| exception instanceof ObjectNotFoundException
				|| exception instanceof UsernameNotFoundException
				|| exception instanceof UsuarioDesativadoException) {
			error.put("Erros", exception.getMessage());
		}else {
			error.put("Erros", "Ocorreu um erro no sistema, contate a equipe técnica");
		}

		return error;
	}



//	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler({CadastroJaExistente.class, IncorrectData.class, MandatoryAttribute.class, ObjectAlreadyInBase.class})
//	public HashMap<String, String> exceptionHandler(Exception exception){
//		HashMap<String, String> error = new HashMap<>();
//		error.put("Erros", exception.getMessage());
//		return error;
//	}
}
