package br.com.sysmap.api.controller.message;

public enum ValidationTransactionMessages {

	CARTAO_INEXISTENTE, SENHA_INVALIDA, SALDO_INSUFICIENTE;

	private String message;

	public String getMessage() {
		return this.message;
	}
}
