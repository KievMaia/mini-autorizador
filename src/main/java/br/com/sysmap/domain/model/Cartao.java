package br.com.sysmap.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Representa o Cartão.
 *
 * @author Kiev Maia.
 *
 */
@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long numeroCartao;

	@Column(nullable = false)
	private String senha;

	private BigDecimal saldo;
	
	/**
	 * Default Construtor
	 */
	public Cartao() {
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
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Cartao [id=" + id + ", numeroCartao=" + numeroCartao + ", senha=" + senha + ", saldo=" + saldo + "]";
	}

}
