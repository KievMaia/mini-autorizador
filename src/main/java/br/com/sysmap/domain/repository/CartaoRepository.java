package br.com.sysmap.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sysmap.domain.model.Cartao;

/**
 * Interface que define os métodos de acesso ao banco de dados para a unidade de
 * cartões.
 *
 * @author Kiev Maia
 *
 */
@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	/**
	 * Método responsável por buscar um cartão pelo seu respectivo número.
	 *
	 * @param numeroCartao {@link Long}: o número do respectivo cartão.
	 */
	Optional<Cartao> findByNumeroCartao(final Long numeroCartao);
}
