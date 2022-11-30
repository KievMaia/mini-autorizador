package br.com.sysmap.domain.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe que responsável por tratar exceções globais em nível de controlador.
 *
 * @author Kiev Maia
 *
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(final EntidadeNaoEncontradaException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(EntidadeJaExisteException.class)
	public ResponseEntity<?> tratarEntidadeJaExisteException(final EntidadeJaExisteException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
	@ExceptionHandler(TransacaoInvalidaException.class)
	public ResponseEntity<?> tratarTransacaoInvalidaException(final TransacaoInvalidaException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY,
				request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		if (body == null) {
			body = CorpoException.builder()
					.withDataHora(LocalDateTime.now())
					.withMessage(statusCode.toString())
					.build();
		} else if (body instanceof String) {
			body = CorpoException.builder()
					.withDataHora(LocalDateTime.now())
					.withMessage((String) body).build();
		}
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
}
