package br.com.sysmap.domain.exception;

public class TransacaoInvalidaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public TransacaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
