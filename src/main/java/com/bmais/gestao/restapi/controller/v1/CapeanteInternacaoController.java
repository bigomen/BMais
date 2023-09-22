package com.bmais.gestao.restapi.controller.v1;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacaoPesquisa;
import com.bmais.gestao.restapi.service.CapeanteInternacaoService;

@Validated
@RestController
@RequestMapping("/capeantes/internacao/v1")
public class CapeanteInternacaoController
{
	private final CapeanteInternacaoService capeanteInternacaoService;
	
	@Autowired
	public CapeanteInternacaoController(
			CapeanteInternacaoService capeanteInternacaoService)
	{
		super();
		this.capeanteInternacaoService = capeanteInternacaoService;
	}
	
	@GetMapping("/lista")
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<RestCapeanteInternacao> lista(RestCapeanteInternacaoPesquisa params)
	{
		return capeanteInternacaoService.lista(params);
	}
	
	@GetMapping("/detalhes/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public RestCapeanteInternacao detalhar(@PathVariable String id)
	{
		return capeanteInternacaoService.detalhar(id);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void novo(@RequestBody RestCapeanteInternacao restCapeanteInternacao)
	{
		capeanteInternacaoService.novo(restCapeanteInternacao);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void atualizar(@PathVariable String id, @Valid @RequestBody RestCapeanteInternacao restCapeanteInternacao)
	{
		capeanteInternacaoService.atualizar(id, restCapeanteInternacao);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void excluir(@PathVariable String id)
	{
		capeanteInternacaoService.excluir(id);
	}
}
