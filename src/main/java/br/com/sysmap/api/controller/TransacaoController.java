package br.com.sysmap.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sysmap.api.controller.dto.TransacaoDTO;
import br.com.sysmap.domain.service.impl.TransacaoServiceImpl;

@RestController
@RequestMapping(value = "/transacoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

	@Autowired
	TransacaoServiceImpl transacaoService;

	@PostMapping
	public ResponseEntity<String> performTansaction(@RequestBody TransacaoDTO transacaoDTO) {
		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.performTransaction(transacaoDTO));
	}
}
