package br.com.sysmap.domain.service;

import br.com.sysmap.api.controller.dto.TransacaoDTO;

/**
 * Interface que define os métodos do serviço de transação.
 *
 * @author Kiev Maia
 */
public interface TransacaoService {

	/**
	 * Método responsável por realizar uma transação.
	 *
	 * @param transacaoDTO {@link TransacaoDTO}: a transação a ser realizada.
	 */
	String performTransaction(final TransacaoDTO transacaoDTO);

}
