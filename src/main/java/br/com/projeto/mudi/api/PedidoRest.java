package br.com.projeto.mudi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.StatusPedido;
import br.com.projeto.mudi.repository.PedidoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRest {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/aguardando")
	public List<Pedido> getPedidos(@PageableDefault(size = 5, sort = { "id" }) Pageable paginacao) {

		return pedidoRepository.findByStatus(StatusPedido.AGUARDANDO, paginacao);

	}
}
