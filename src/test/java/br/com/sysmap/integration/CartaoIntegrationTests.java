package br.com.sysmap.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.domain.exception.EntidadeNaoEncontradaException;
import br.com.sysmap.domain.service.impl.CartaoServiceImpl;

@SpringBootTest
class CartaoIntegrationTests {

	@Autowired
	CartaoServiceImpl serviceImpl;
	
	@Test
	public void testarCadastroCartao_ComSucesso() {
		CartaoDTO dto = new CartaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenha("12345");
		
		dto = serviceImpl.salvar(dto);
		
		assertThat(dto).isNotNull();
		assertThat(dto.getId()).isNotNull();
		assertThat(dto.getSaldo()).isEqualTo("500.00");
	}

	@Test
	public void testarCadastroCartao_SemNumero() {
		CartaoDTO dto = new CartaoDTO();
		dto.setNumeroCartao(null);
		dto.setSenha("12345");
		
		DataIntegrityViolationException erroEsperado = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			serviceImpl.salvar(dto);
		});
		
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void testarCadastroCartao_SemSenha() {
		CartaoDTO dto = new CartaoDTO();
		dto.setNumeroCartao(121515163611L);
		dto.setSenha(null);
		
		DataIntegrityViolationException erroEsperado = Assertions.assertThrows(DataIntegrityViolationException.class, () -> {
			serviceImpl.salvar(dto);
		});
		
		assertThat(erroEsperado).isNotNull();
	}
	
	@Test
	public void testarObterSaldoCartao_ComSucesso() {
		BigDecimal saldoCartao = serviceImpl.getSaldoCartao(121515161611L);
		
		assertThat(saldoCartao).isNotNull();
		assertThat(saldoCartao).isEqualTo("500.00");
	}
	
	@Test
	public void testarObterSaldoCartao_NumeroInexistente() {
		EntidadeNaoEncontradaException erroEsperado = Assertions.assertThrows(EntidadeNaoEncontradaException.class, () -> {
			serviceImpl.getSaldoCartao(1215151112611L);
		});
		
		assertThat(erroEsperado).isNotNull();
	}
}
