package br.com.sysmap.integration;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.api.controller.dto.TransacaoDTO;
import br.com.sysmap.domain.exception.TransacaoInvalidaException;
import br.com.sysmap.domain.service.impl.CartaoServiceImpl;
import br.com.sysmap.domain.service.impl.TransacaoServiceImpl;

@SpringBootTest
public class TransacaoIntegrationTests {

	@Autowired
	TransacaoServiceImpl transacaoServiceImpl;
	
	@Autowired
	CartaoServiceImpl cartaoServiceImpl;

	@Test
	public void testarTransacao_ComSucesso() {
		this.salvaCartao();
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenhaCartao("12345");
		dto.setValor(new BigDecimal("400.00"));
		
		String performTransaction = transacaoServiceImpl.performTransaction(dto);
		
		assertThat(performTransaction).isNotNull();
	}
	
	@Test
	public void testarTransacao_SemNumeroCartao() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(null);
		dto.setSenhaCartao("12345");
		dto.setValor(new BigDecimal("400.00"));
		
		TransacaoInvalidaException erroEsperado = Assertions.assertThrows(TransacaoInvalidaException.class, () -> {
			transacaoServiceImpl.performTransaction(dto);
		});
		
		assertThat(erroEsperado).isNotNull();		
	}
	
	@Test
	public void testarTransacao_NumeroCartaoInexistente() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(1215151223461611L);
		dto.setSenhaCartao("12345");
		dto.setValor(new BigDecimal("400.00"));
		
		TransacaoInvalidaException erroEsperado = Assertions.assertThrows(TransacaoInvalidaException.class, () -> {
			transacaoServiceImpl.performTransaction(dto);
		});
		
		assertThat(erroEsperado).isNotNull();		
	}
	
	@Test
	public void testarTransacao_SenhaInvalida() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenhaCartao("12347");
		dto.setValor(new BigDecimal("400.00"));
		
		TransacaoInvalidaException erroEsperado = Assertions.assertThrows(TransacaoInvalidaException.class, () -> {
			transacaoServiceImpl.performTransaction(dto);
		});
		
		assertThat(erroEsperado).isNotNull();	
	}
	
	@Test
	public void testarTransacao_SaldoInsuficiente() {
		TransacaoDTO dto = new TransacaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenhaCartao("12345");
		dto.setValor(new BigDecimal("500.01"));
		
		TransacaoInvalidaException erroEsperado = Assertions.assertThrows(TransacaoInvalidaException.class, () -> {
			transacaoServiceImpl.performTransaction(dto);
		});
		
		assertThat(erroEsperado).isNotNull();	
	}

	private void salvaCartao() {
		CartaoDTO dto = new CartaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenha("12345");
		this.cartaoServiceImpl.salvar(dto);
	}
}
