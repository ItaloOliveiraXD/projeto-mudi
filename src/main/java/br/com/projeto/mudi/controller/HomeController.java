package br.com.projeto.mudi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.StatusPedido;
import br.com.projeto.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	public ModelAndView home() {

		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.ENTREGUE);

		ModelAndView mv = new ModelAndView("home");

		mv.addObject("pedidos", pedidos);

		return mv;
	}

}
