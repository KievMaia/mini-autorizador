package br.com.sysmap.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.domain.service.CartaoServiceImpl;

/**
 * Classe que contém os endpoints do da unidade Cartão.
 *
 * @author Kiev Maia
 *
 */
@RestController
@RequestMapping(value = "/cartoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartaoController {
	
	@Autowired
	CartaoServiceImpl cartaoService;

	/**
	 * Método da API que adiciona um cartão em uma estrutura JSON.
	 *
	 * @param cartaoDTO {@link CartaoDTO}: o cartão a ser cadastrado.
	 * @return {@link ResponseEntity<CartaoDTO>} status e corpo da execução.
	 */
	@PostMapping
	public ResponseEntity<CartaoDTO> adicionar(@RequestBody final CartaoDTO cartaoDTO) throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(this.cartaoService.salvar(cartaoDTO));	
	}
	
	@GetMapping(path = "/{numeroCartao}")
	public ResponseEntity<BigDecimal> getSaldoCartao(@PathVariable Long numeroCartao) throws Exception{
		return ResponseEntity.ok(cartaoService.getSaldoCartao(numeroCartao));
	}
}