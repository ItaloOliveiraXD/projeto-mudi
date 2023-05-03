package br.com.projeto.mudi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	List<Pedido> findByStatus(StatusPedido status);

}
