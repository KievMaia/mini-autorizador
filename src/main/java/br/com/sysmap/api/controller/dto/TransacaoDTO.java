package br.com.sysmap.api.controller.dto;

import java.math.BigDecimal;

/**
 * O objeto de transferência que representa uma transação.
 * 
 * @author Kiev Maia
 *
 */
public class TransacaoDTO {

	private Long numeroCartao;
	private String senhaCartao;
	private BigDecimal valor;
	
	/**
	 * Default constructor
	 */
	public TransacaoDTO() {
	}

	/**
	 * 
	 * @return {@link Long}: número do cartão.
	 */
	public Long getNumeroCartao() {
		return numeroCartao;
	}

	/**
	 * 
	 * @param {@link Long}: número do cartão.
	 */
	public void setNumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	/**
	 * 
	 * @return {@link String}: senha do cartão.
	 */
	public String getSenhaCartao() {
		return senhaCartao;
	}

	/**
	 * 
	 * @param {@link String}: senha do cartão.
	 */
	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}

	/**
	 * 
	 * @return {@link BigDecimal}: saldo do cartão.
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * 
	 * @param {@link BigDecimal}: saldo do cartão.
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
