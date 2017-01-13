package com.controle.boleto.thymeleaf;


import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.controle.boleto.thymeleaf.processor.ClassForErrorAttributeTagProcessor;

public class BoletoDialect extends AbstractProcessorDialect {

	public BoletoDialect() {
		super("Projeto Boleto", "PersonalFinan", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		return processadores;
	}
}
