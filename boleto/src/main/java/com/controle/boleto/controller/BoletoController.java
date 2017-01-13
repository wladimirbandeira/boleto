package com.controle.boleto.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.boleto.controller.service.CadastroBoletoService;
import com.controle.boleto.model.Boleto;
import com.controle.boleto.model.StatusBoleto;
import com.controle.boleto.respository.Boletos;

@Controller
@RequestMapping("/boleto")
public class BoletoController {

	private static final String CADASTRO_VIEW = "boleto/CadastroBoleto";

	@Autowired
	private Boletos boletos;
	
	@Autowired
	private CadastroBoletoService cadastroBoletoService;
	
	

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Boleto());
		return mv;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String cadastrar(@Validated Boleto boleto, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}

		try {
			cadastroBoletoService.salvar(boleto);
			attributes.addFlashAttribute("mensagem", "Boleto Salvo com Sucesso!");
			return "redirect:/boleto/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataBoleto", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}

	
	// pesquisa
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisa() {
		List<Boleto> todosBoletos = boletos.findAll();
		ModelAndView mv = new ModelAndView("/boleto/PesquisaBoleto");
		mv.addObject("boletos", todosBoletos);
		return mv;
	}

	//editar
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Boleto boleto) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(boleto);
		return mv;
	}

	@ModelAttribute("todosStatusBoleto")
	public List<StatusBoleto> todosStatusBoleto() {
		return Arrays.asList(StatusBoleto.values());
	}

	
	//excluir
	@RequestMapping(value = "{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroBoletoService.excluir(codigo);

		attributes.addFlashAttribute("mensagem", "Cheques excluído com sucesso!");
		return "redirect:/boleto/pesquisa";
	}
	
	
	@RequestMapping(value = "/{codigo}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long codigo) {
		return cadastroBoletoService.receber(codigo);
	}

	

}
