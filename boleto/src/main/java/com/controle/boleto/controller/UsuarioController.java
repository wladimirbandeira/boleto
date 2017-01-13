package com.controle.boleto.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.boleto.controller.service.CadastroUsuarioService;
import com.controle.boleto.model.Usuario;
import com.controle.boleto.respository.Grupos;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private Grupos grupos;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("usuario/CadastroUsuario");
		mv.addObject("grupos", grupos.findAll());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(usuario);
		}

		cadastroUsuarioService.salvar(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso");

		return new ModelAndView("redirect:/usuarios/novo");

	}

}
