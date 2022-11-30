package br.com.sysmap.domain.service;

import java.math.BigDecimal;

import br.com.sysmap.api.controller.dto.CartaoDTO;

/**
 * Interface que define os métodos do serviço de cartão.
 *
 * @author Kiev Maia
 */
public interface CartaoService {

	/**
	 * Método responsável por adicionar um cartão.
	 *
	 * @param cartaoDTO {@link CartaoDTO}: o cartão a ser inserido.
	 */
	CartaoDTO salvar(final CartaoDTO cartaoDTO);

	/**
	 * Método responsável por retornar o saldo do cartão.
	 *
	 * @param numeroCartao {@link Long}: o número do cartão.
	 */
	BigDecimal getSaldoCartao(final Long numeroCartao);

	/**
	 * Método responsável por atualizar as informações do saldo do cartão.
	 *
	 * @param cartaoDTO {@link CartaoDTO}: o cartão a ser atualizado.
	 */
	void atualizar(final CartaoDTO cartaoDTO);

}
