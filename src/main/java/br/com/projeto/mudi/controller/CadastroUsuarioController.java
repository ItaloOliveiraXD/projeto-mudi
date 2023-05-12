package br.com.projeto.mudi.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.projeto.mudi.dto.CadastroDto;

@Controller
@RequestMapping("/usuario")
public class CadastroUsuarioController {

	@GetMapping("/novoCadastro")
	public ModelAndView formCadastroUsuario() {
		ModelAndView mv = new ModelAndView("/usuario/cadastroUsuario");
		return mv;
	}

	@PostMapping("/cadastraUsuario")
    public String cadastrar(@Valid CadastroDto cadastroDto, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			return "/usuario/cadastroUsuario";
		}
		
		System.out.println(cadastroDto.password());
        return "redirect:/usuario/novoCadastro";
    }
}
