package com.bmais.gestao.restapi.controller.v1;

import java.math.BigDecimal;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.bmais.gestao.restapi.restmodel.RestMovimentacaoBancaria;
import com.bmais.gestao.restapi.restmodel.RestTipoMovimentacaoBancaria;
import com.bmais.gestao.restapi.service.MovimentacaoBancariaService;

@RestController
@RequestMapping("/movimentacao/bancaria/v1")
public class MovimentacaoBancariaController
{
	private final MovimentacaoBancariaService service;
	
	@Autowired
	public MovimentacaoBancariaController(MovimentacaoBancariaService service)
	{
		super();
		this.service = service;
	}
	
	@GetMapping("/tipos")
	public Collection<RestTipoMovimentacaoBancaria> listaTiposMovimentacao()
	{
		return service.listaTiposMovimentacao();
	}
	
	@GetMapping("/lista")
	public Collection<RestMovimentacaoBancaria> lista(@RequestParam(required = false) String dadosBancarios, @RequestParam(required = false) BigDecimal valor)
	{
		return service.lista(dadosBancarios, valor);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.OK)
	public void nova(@RequestBody RestMovimentacaoBancaria restMovimentacaoBancaria)
	{
		service.nova(restMovimentacaoBancaria);
	}
}
