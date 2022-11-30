package br.com.sysmap.domain.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sysmap.api.controller.dto.TransacaoDTO;
import br.com.sysmap.api.controller.message.ValidationTransactionMessages;
import br.com.sysmap.domain.exception.TransacaoInvalidaException;
import br.com.sysmap.domain.model.Cartao;
import br.com.sysmap.domain.service.CartaoService;
import br.com.sysmap.domain.service.TransacaoService;
import br.com.sysmap.domain.util.CartaoObjectUtil;

/**
 * Implementação do serviço ({@link CartaoService}) da unidade Cartão.
 *
 * @author Kiev Maia
 *
 */
@Service
public class TransacaoServiceImpl implements TransacaoService{

	@Autowired
	CartaoServiceImpl cartaoService;

	@Override
	public String performTransaction(final TransacaoDTO transacaoDTO) {
		Optional<Cartao> cartaoRecuperado = cartaoService.findByNumeroCartao(transacaoDTO.getNumeroCartao());
		
		synchronized (this) {
			this.validadeTransaction(transacaoDTO);
			if (cartaoRecuperado.isPresent()) {
				this.subtracaoSaldo(transacaoDTO, cartaoRecuperado);
			}
		}
		return "OK";
	}

	private void subtracaoSaldo(final TransacaoDTO transacaoDTO, final Optional<Cartao> cartaoRecuperado) {
		BigDecimal valorTotal = cartaoRecuperado.get().getSaldo().subtract(transacaoDTO.getValor());
		cartaoRecuperado.get().setSaldo(valorTotal);
		cartaoService.atualizar(CartaoObjectUtil.toDto().apply(cartaoRecuperado.get()));
	}

	private void validadeTransaction(final TransacaoDTO transacaoDTO) {
		Optional<Cartao> cartao = cartaoService.findByNumeroCartao(transacaoDTO.getNumeroCartao());
		if (cartao.isEmpty()) {
			throw new TransacaoInvalidaException(ValidationTransactionMessages.CARTAO_INEXISTENTE.toString());
		}
		if (!transacaoDTO.getSenhaCartao().equals(cartao.get().getSenha())) {
			throw new TransacaoInvalidaException(ValidationTransactionMessages.SENHA_INVALIDA.toString());
		}
		if (transacaoDTO.getValor().compareTo(cartao.get().getSaldo()) == 1) {
			throw new TransacaoInvalidaException(ValidationTransactionMessages.SALDO_INSUFICIENTE.toString());
		}
	}
}
