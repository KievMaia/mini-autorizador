package br.com.sysmap.domain.util;

import java.math.BigDecimal;
import java.util.function.Function;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.domain.model.Cartao;

/**
 * Implementação da classe onde contém as funções de conversão de objeto para dto e vice-versa.
 *
 * @author Kiev Maia
 *
 */
public class CartaoObjectUtil {

	private CartaoObjectUtil() {
		throw new IllegalAccessError();
	}
	
	public static final Function<CartaoDTO, Cartao> toNewCard() {
		return cartaoDTO -> {
			final var cartao = new Cartao();
			cartao.setNumeroCartao(cartaoDTO.getNumeroCartao());
			cartao.setSenha(cartaoDTO.getSenha());
			cartao.setSaldo(new BigDecimal("500.00"));
			return cartao;
		};
	}

	public static final Function<Cartao, CartaoDTO> toDto() {
		return entity -> {
			final var cartaoDTO = new CartaoDTO();
			cartaoDTO.setId(entity.getId());
			cartaoDTO.setNumeroCartao(entity.getNumeroCartao());
			cartaoDTO.setSenha(entity.getSenha());
			cartaoDTO.setSaldo(entity.getSaldo());
			return cartaoDTO;
		};
	}
}
