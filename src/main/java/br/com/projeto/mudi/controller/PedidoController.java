package br.com.projeto.mudi.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.mudi.dto.NovoPedidoDto;
import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.models.User;
import br.com.projeto.mudi.repository.PedidoRepository;
import br.com.projeto.mudi.repository.UserRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/formulario")
	public String formulario(NovoPedidoDto novoPedidoDto) {

		return "pedido/formulario";
	}

	@Transactional
	@PostMapping("/novo")
	public String novoPedido(@Valid NovoPedidoDto novoPedidoDto, BindingResult resultado) {

		if (resultado.hasErrors()) {
			return "pedido/formulario";
		}

		String usuario = SecurityContextHolder.getContext().getAuthentication().getName();
		
		User user = userRepository.findByUsername(usuario);
		pedidoRepository.save(new Pedido(novoPedidoDto, user));

		return "redirect:/home";
	}
}
