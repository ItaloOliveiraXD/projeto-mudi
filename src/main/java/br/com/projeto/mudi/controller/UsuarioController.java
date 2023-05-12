package br.com.projeto.mudi.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.StatusPedido;
import br.com.projeto.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping("/pedidos")
	public ModelAndView pedidos(Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());

		ModelAndView mv = new ModelAndView("usuario/home");

		mv.addObject("pedidos", pedidos);

		return mv;
	}

	@GetMapping("pedidos/{status}")
	public ModelAndView status(@PathVariable("status") String status, Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findByStatusAndUsuario(StatusPedido.valueOf(status.toUpperCase()),
				principal.getName());

		ModelAndView mv = new ModelAndView("usuario/home");

		mv.addObject("pedidos", pedidos);
		mv.addObject("status", status);
		return mv;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
}
