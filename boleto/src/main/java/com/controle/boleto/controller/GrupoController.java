package com.controle.boleto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.controle.boleto.controller.service.CadastroGrupoService;
import com.controle.boleto.controller.service.exception.NomeGrupoCadastradoException;
import com.controle.boleto.model.Grupo;
import com.controle.boleto.respository.Grupos;

@Controller
@RequestMapping("/grupos")
public class GrupoController {
	

	@Autowired
	private Grupos grupos;
	
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Grupo grupo) {
		ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
		mv.addObject("grupos", grupos.findAll());
		return mv;
		
	}
	
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(grupo);
		}
	
		
		try{
		cadastroGrupoService.salvar(grupo);
		}catch (NomeGrupoCadastradoException e) {
		//	result.rejectValue("nome", e.getMessage(), e.getMessage());
			result.rejectValue("codigogrupo", e.getMessage(), e.getMessage());
			return novo(grupo);
		}
		
		attributes.addFlashAttribute("mensagem", "Grupo salvo com sucesso");
		return new ModelAndView("redirect:/grupos/novo");
	
	}
	
	
//	@RequestMapping("/pesquisa")
//	public String pesquisa(){
//		return "grupo/PesquisaGrupo";
//	}
	
	// pesquisa
	@RequestMapping("/pesquisa")
	public ModelAndView pesquisa() {
		List<Grupo> todosGrupos = grupos.findAll();
		ModelAndView mv = new ModelAndView("/grupo/PesquisaGrupo");
		mv.addObject("grupos", todosGrupos);
		return mv;
	}
	
	
	//editar
		@RequestMapping("{codigo}")
		public ModelAndView edicao(@PathVariable("codigo") Grupo grupo) {
			ModelAndView mv = new ModelAndView("grupo/CadastroGrupo");
			mv.addObject(grupo);
			return mv;
		}
	
	
	
}
