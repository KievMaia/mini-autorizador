package br.com.sysmap.api.controller.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * O objeto de transferência que representa o cartão.
 * 
 * @author Kiev Maia
 *
 */
public class CartaoDTO {

	@JsonIgnore
	private Long id;

	private Long numeroCartao;

	private String senha;

	@JsonIgnore
	private BigDecimal saldo;

	/**
	 * Default constructor
	 */
	public CartaoDTO() {
	}

	/**
	 * 
	 * @return {@link Long}: identificador do cartão.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param {@link Long}: identificador do cartão.
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return {@link Long}: senha do cartão.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * 
	 * @param {@link Long}: senha do cartão.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * 
	 * @return {@link Long}: saldo do cartão.
	 */
	public BigDecimal getSaldo() {
		return saldo;
	}

	/**
	 * 
	 * @param {@link Long}: saldo do cartão.
	 */
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "CartaoDTO [id=" + id + ", numeroCartao=" + numeroCartao + ", senha=" + senha + ", saldo=" + saldo + "]";
	}

}
