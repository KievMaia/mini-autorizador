package br.com.sysmap.unit;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.com.sysmap.api.controller.dto.CartaoDTO;
import br.com.sysmap.domain.service.impl.CartaoServiceImpl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransacaoUT {

	@LocalServerPort
	private int port;
	
	@Autowired
	CartaoServiceImpl cartaoServiceImpl;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/transacoes";
	}
	
	@Test
	@Order(value = 1)
	public void deveRetornarStatus201_QuandoTransacaoSucesso() {
		this.salvaCartao();
		given()
			.body("{ \"numeroCartao\": \"121515161611\", \"senhaCartao\": \"12345\", \"valor\": \"10.00\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	@Order(value = 2)
	public void deveRetornarStatus422_QuandoTransacaoNumeroCartaoInexistente() {
		given()
			.body("{ \"numeroCartao\": \"1215151616110\", \"senhaCartao\": \"12345\", \"valor\": \"10.00\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.body("message", Matchers.equalTo("CARTAO_INEXISTENTE"))
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	@Test
	@Order(value = 3)
	public void deveRetornarStatus422_QuandoTransacaoSenhaInvalida() {
		given()
			.body("{ \"numeroCartao\": \"121515161611\", \"senhaCartao\": \"123466\", \"valor\": \"10.00\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.body("message", Matchers.equalTo("SENHA_INVALIDA"))
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	@Test
	@Order(value = 3)
	public void deveRetornarStatus422_QuandoTransacaoSaldoInsuficiente() {
		given()
			.body("{ \"numeroCartao\": \"121515161611\", \"senhaCartao\": \"12345\", \"valor\": \"500.01\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.body("message", Matchers.equalTo("SALDO_INSUFICIENTE"))
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	private void salvaCartao() {
		CartaoDTO dto = new CartaoDTO();
		dto.setNumeroCartao(121515161611L);
		dto.setSenha("12345");
		this.cartaoServiceImpl.salvar(dto);
	}
}
