package com.bmais.gestao.restapi.controller.v1;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.bmais.gestao.restapi.restmodel.RestCliente;
import com.bmais.gestao.restapi.restmodel.RestHospital;
import com.bmais.gestao.restapi.restmodel.RestServico;
import com.bmais.gestao.restapi.restmodel.RestVinculo;
import com.bmais.gestao.restapi.service.AuditorService;
import com.bmais.gestao.restapi.service.ClienteService;
import com.bmais.gestao.restapi.service.HospitalService;
import com.bmais.gestao.restapi.service.ServicoService;

@RestController
@RequestMapping("/vinculos/v1")
@Validated
public class VinculoController
{
	private final ClienteService clienteService;
	private final HospitalService hospitalService;
	private final ServicoService servicoService;
	private final AuditorService auditorService;

	@Autowired
	public VinculoController(ClienteService clienteService, HospitalService hospitalService, ServicoService servicoService, AuditorService auditorService)
	{
		super();
		this.clienteService = clienteService;
		this.hospitalService = hospitalService;
		this.servicoService = servicoService;
		this.auditorService = auditorService;
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/clientes")
	public Collection<RestCliente> pesquisarClientes()
	{
		return clienteService.pesquisarClientesVinculos();
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/hospitais")
	public Collection<RestHospital> pesquisarHospitais(@RequestParam String cliente)
	{
		return hospitalService.pesquisarHospitaisVinculos(cliente);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/servicos")
	public Collection<RestServico> pesquisarServicos(@RequestParam String cliente, @RequestParam String hospital)
	{
		return servicoService.pesquisarServicosVinculos(cliente, hospital);
	}

	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(value = "/auditores")
	public Map<String, List<RestVinculo>> pesquisarauditores(@RequestParam String cliente, @RequestParam String hospital, @RequestParam String servico)
	{
		return auditorService.pesquisarAuditoresVinculos(cliente, hospital, servico);
	}

}
