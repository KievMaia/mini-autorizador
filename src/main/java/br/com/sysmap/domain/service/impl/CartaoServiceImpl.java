package br.com.sysmap.domain.service.impl;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.domain.exception.EntidadeJaExisteException;
import br.com.sysmap.domain.exception.EntidadeNaoEncontradaException;
import br.com.sysmap.domain.model.Cartao;
import br.com.sysmap.domain.repository.CartaoRepository;
import br.com.sysmap.domain.service.CartaoService;
import br.com.sysmap.domain.util.CartaoObjectUtil;

/**
 * Implementação do serviço ({@link CartaoService}) da unidade Cartão.
 *
 * @author Kiev Maia
 *
 */
@Service
public class CartaoServiceImpl implements CartaoService{

	@Autowired
	CartaoRepository cartaoRepository;

	@Override
	@Transactional
	public CartaoDTO salvar(final CartaoDTO cartaoDTO) {
		Cartao cartao = CartaoObjectUtil.toNewCard().apply(cartaoDTO);
		
		if (findByNumeroCartao(cartaoDTO.getNumeroCartao()).isPresent()) {
			throw new EntidadeJaExisteException("Um cartão com esse número já existe!");
		}

		return CartaoObjectUtil.toDto().apply(cartaoRepository.save(cartao));
	}

	public Optional<Cartao> findByNumeroCartao(final Long numeroCartao) {
		return cartaoRepository.findByNumeroCartao(numeroCartao);
	}

	@Override
	public BigDecimal getSaldoCartao(final Long numeroCartao)  {
		Cartao cartao = findByNumeroCartao(numeroCartao)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Um cartão com este número não existe!"));

		return cartao.getSaldo();
	}
	
	@Override
	public void atualizar(CartaoDTO cartaoDTO) {
		Cartao cartao = CartaoObjectUtil.toEntity().apply(cartaoDTO);
		this.cartaoRepository.save(cartao);
	}
}
