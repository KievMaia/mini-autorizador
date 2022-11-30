package br.com.sysmap.unit;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastroCartaoUT {
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cartoes";
	}

	@Test
	@Order(value = 1)
	public void deveRetornarStatus201_QuandoCadastrarCartao() {
		given()
			.body("{ \"numeroCartao\": \"6549873025634501\", \"senha\": \"1234\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	@Order(value = 2)
	public void deveRetornarStatus422_QuandoCadastrarCartaoJaExistente() {
		given()
			.body("{ \"numeroCartao\": \"6549873025634501\", \"senha\": \"1234\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.body("message", Matchers.equalTo("Um cartão com esse número já existe!"))
			.statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
	}
	
	@Test
	@Order(value = 3)
	public void deveRetornarStatus200_QuandoConsultarSaldo() {
		given()
			.pathParam("numeroCartao", 6549873025634501L)
			.accept(ContentType.JSON)
		.when()
			.get("/{numeroCartao}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
}
