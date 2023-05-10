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
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping
	public ModelAndView home(Principal principal) {

		List<Pedido> pedidos = pedidoRepository.findAllByUsuario(principal.getName());

		ModelAndView mv = new ModelAndView("home");

		mv.addObject("pedidos", pedidos);

		return mv;
	}

	@GetMapping("/{status}")
	public ModelAndView status(@PathVariable("status") String status) {

		List<Pedido> pedidos = pedidoRepository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));

		ModelAndView mv = new ModelAndView("home");

		mv.addObject("pedidos", pedidos);
        mv.addObject("status", status);
		return mv;
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public String onError() {
		return "redirect:/home";
	}
	
}
