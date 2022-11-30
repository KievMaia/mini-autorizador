package br.com.sysmap.domain.service;

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

}
