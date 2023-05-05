package br.com.projeto.mudi.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.mudi.dto.NovoPedidoDto;
import br.com.projeto.mudi.models.Pedido;
import br.com.projeto.mudi.repository.PedidoRepository;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

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

		pedidoRepository.save(new Pedido(novoPedidoDto));

		return "redirect:/home";
	}
}
