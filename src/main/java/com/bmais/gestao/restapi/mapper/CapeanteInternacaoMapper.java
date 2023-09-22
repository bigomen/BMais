package com.bmais.gestao.restapi.mapper;

import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import com.bmais.gestao.restapi.model.CapeanteInternacao;
import com.bmais.gestao.restapi.model.projections.CapeanteInternacaoProjection;
import com.bmais.gestao.restapi.restmodel.RestCapeanteInternacao;
import com.bmais.gestao.restapi.utility.UtilSecurity;

@Mapper(uses = {UtilSecurity.class, ResumoMapper.class, ProntuarioCapeanteMapper.class, 
		ProcedimentoCapeanteMapper.class, UsuarioMapper.class, AuditorMapper.class, CIDMapper.class, StatusCapeanteMapper.class}, 
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface CapeanteInternacaoMapper
{
	CapeanteInternacaoMapper INSTANCE = Mappers.getMapper(CapeanteInternacaoMapper.class);
	
	@Mappings(value = {
			@Mapping(target = "rn", source = "internado.rn"),
			@Mapping(target = "nomeRn", source = "internado.nomeRn"),
			@Mapping(target = "gemelar", source = "internado.gemelar"),
			@Mapping(target = "dataNascimentoRn", source = "internado.dataNascimentoRn"),
			@Mapping(target = "sexoRn", source = "internado.sexoRn"),
			@Mapping(target = "parcial", source = "internado.parcial"),
			@Mapping(target = "primeiraParcial", source = "internado.primeiraParcial"),
			@Mapping(target = "parcialFinal", source = "internado.parcialFinal"),
			@Mapping(target = "inicioCobranca", source = "internado.inicioCobranca"),
			@Mapping(target = "fimCobranca", source = "internado.fimCobranca"),
			@Mapping(target = "complemento", source = "internado.complemento"),
			@Mapping(target = "pacote", source = "internado.pacote"),
			@Mapping(target = "dayclinic", source = "internado.dayclinic"),
			@Mapping(target = "observacao", source = "internado.observacao"),
			@Mapping(target = "medico", source = "internado.medico", qualifiedByName = "toAuditor"),
			@Mapping(target = "enfermeiro", source = "internado.enfermeiro", qualifiedByName = "toAuditor"),
			@Mapping(target = "status", source = "internado.status"),
			@Mapping(target = "cid", source = "internado.cid", qualifiedByName = "toCID"),
			@Mapping(target = "tipoInternacao", source = "internado.tipoInternacao"),
			@Mapping(target = "tipoTratamento", source = "internado.tipoTratamento"),
			@Mapping(target = "usuario", ignore = true),
			@Mapping(target = "resumo", source = "resumo"),
			@Mapping(target = "resumo.apdiariaCobrada", source = "diarias.apdiariaCobrada"),
			@Mapping(target = "resumo.apDiariaGlosada", source = "diarias.apDiariaGlosada"),
			@Mapping(target = "resumo.apValorApresentado", source = "diarias.apValorApresentado"),
			@Mapping(target = "resumo.apValorGlosado", source = "diarias.apValorGlosado"),
			@Mapping(target = "resumo.enfDiariaCobrada", source = "diarias.enfDiariaCobrada"),
			@Mapping(target = "resumo.enfDiariaGlosada", source = "diarias.enfDiariaGlosada"),
			@Mapping(target = "resumo.enfValorApresentado", source = "diarias.enfValorApresentado"),
			@Mapping(target = "resumo.enfValorGlosado", source = "diarias.enfValorGlosado"),
			@Mapping(target = "resumo.semiDiariaCobrada", source = "diarias.semiDiariaCobrada"),
			@Mapping(target = "resumo.semiDiariaglosada", source = "diarias.semiDiariaglosada"),
			@Mapping(target = "resumo.semiValorApresentado", source = "diarias.semiValorApresentado"),
			@Mapping(target = "resumo.semiValorGlosado", source = "diarias.semiValorGlosado"),
			@Mapping(target = "resumo.utiDiariaCobrada", source = "diarias.utiDiariaCobrada"),
			@Mapping(target = "resumo.utiDiariaGlosada", source = "diarias.utiDiariaGlosada"),
			@Mapping(target = "resumo.utiValorApresentado", source = "diarias.utiValorApresentado"),
			@Mapping(target = "resumo.utiValorGlosado", source = "diarias.utiValorGlosado"),
			@Mapping(target = "resumo.bercarioDiariaCobrada", source = "diarias.bercarioDiariaCobrada"),
			@Mapping(target = "resumo.bercarioDiariaGlosada", source = "diarias.bercarioDiariaGlosada"),
			@Mapping(target = "resumo.bercarioValorApresentado", source = "diarias.bercarioValorApresentado"),
			@Mapping(target = "resumo.bercarioValorGlosado", source = "diarias.bercarioValorGlosado"),
			@Mapping(target = "resumo.dayClinicDiariaCobrada", source = "diarias.dayClinicDiariaCobrada"),
			@Mapping(target = "resumo.dayClinicDiariaGlosada", source = "diarias.dayClinicDiariaGlosada"),
			@Mapping(target = "resumo.dayClinicValorApresentado", source = "diarias.dayClinicValorApresentado"),
			@Mapping(target = "resumo.dayClinicValorGlosado", source = "diarias.dayClinicValorGlosado"),
			@Mapping(target = "resumo.acompanhanteDiariaCobrada", source = "diarias.acompanhanteDiariaCobrada"),
			@Mapping(target = "resumo.acompanhanteDiariaGlosada", source = "diarias.acompanhanteDiariaGlosada"),
			@Mapping(target = "resumo.acompanhanteValorApresentado", source = "diarias.acompanhanteValorApresentado"),
			@Mapping(target = "resumo.acompanhanteValorGlosado", source = "diarias.acompanhanteValorGlosado"),
			@Mapping(target = "resumo.isolamento", source = "diarias.isolamento"),
			@Mapping(target = "resumo.observacao", source = "diarias.observacao")
	})
	CapeanteInternacao convertRestToModel(RestCapeanteInternacao restCapeanteInternacao);
	
	@Mappings(value = {
			@Mapping(source = "rn", target = "internado.rn"),
			@Mapping(source = "nomeRn", target = "internado.nomeRn"),
			@Mapping(source = "gemelar", target = "internado.gemelar"),
			@Mapping(source = "dataNascimentoRn", target = "internado.dataNascimentoRn"),
			@Mapping(source = "sexoRn", target = "internado.sexoRn"),
			@Mapping(source = "parcial", target = "internado.parcial"),
			@Mapping(source = "primeiraParcial", target = "internado.primeiraParcial"),
			@Mapping(source = "parcialFinal", target = "internado.parcialFinal"),
			@Mapping(source = "inicioCobranca", target = "internado.inicioCobranca"),
			@Mapping(source = "fimCobranca", target = "internado.fimCobranca"),
			@Mapping(source = "complemento", target = "internado.complemento"),
			@Mapping(source = "pacote", target = "internado.pacote"),
			@Mapping(source = "dayclinic", target = "internado.dayclinic"),
			@Mapping(source = "observacao", target = "internado.observacao"),
			@Mapping(source = "medico", target = "internado.medico", qualifiedByName = "toAuditorSimples"),
			@Mapping(source = "enfermeiro", target = "internado.enfermeiro", qualifiedByName = "toAuditorSimples"),
			@Mapping(source = "status", target = "internado.status"),
			@Mapping(source = "cid", target = "internado.cid"),
			@Mapping(source = "tipoInternacao", target = "internado.tipoInternacao"),
			@Mapping(source = "tipoTratamento", target = "internado.tipoTratamento"),
			@Mapping(source = "usuario.email", target = "usuario"),
			@Mapping(source = "resumo", target = "resumo"),
			@Mapping(source = "resumo.apdiariaCobrada", target = "diarias.apdiariaCobrada"),
			@Mapping(source = "resumo.apDiariaGlosada", target = "diarias.apDiariaGlosada"),
			@Mapping(source = "resumo.apValorApresentado", target = "diarias.apValorApresentado"),
			@Mapping(source = "resumo.apValorGlosado", target = "diarias.apValorGlosado"),
			@Mapping(source = "resumo.enfDiariaCobrada", target = "diarias.enfDiariaCobrada"),
			@Mapping(source = "resumo.enfDiariaGlosada", target = "diarias.enfDiariaGlosada"),
			@Mapping(source = "resumo.enfValorApresentado", target = "diarias.enfValorApresentado"),
			@Mapping(source = "resumo.enfValorGlosado", target = "diarias.enfValorGlosado"),
			@Mapping(source = "resumo.semiDiariaCobrada", target = "diarias.semiDiariaCobrada"),
			@Mapping(source = "resumo.semiDiariaglosada", target = "diarias.semiDiariaglosada"),
			@Mapping(source = "resumo.semiValorApresentado", target = "diarias.semiValorApresentado"),
			@Mapping(source = "resumo.semiValorGlosado", target = "diarias.semiValorGlosado"),
			@Mapping(source = "resumo.utiDiariaCobrada", target = "diarias.utiDiariaCobrada"),
			@Mapping(source = "resumo.utiDiariaGlosada", target = "diarias.utiDiariaGlosada"),
			@Mapping(source = "resumo.utiValorApresentado", target = "diarias.utiValorApresentado"),
			@Mapping(source = "resumo.utiValorGlosado", target = "diarias.utiValorGlosado"),
			@Mapping(source = "resumo.bercarioDiariaCobrada", target = "diarias.bercarioDiariaCobrada"),
			@Mapping(source = "resumo.bercarioDiariaGlosada", target = "diarias.bercarioDiariaGlosada"),
			@Mapping(source = "resumo.bercarioValorApresentado", target = "diarias.bercarioValorApresentado"),
			@Mapping(source = "resumo.bercarioValorGlosado", target = "diarias.bercarioValorGlosado"),
			@Mapping(source = "resumo.dayClinicDiariaCobrada", target = "diarias.dayClinicDiariaCobrada"),
			@Mapping(source = "resumo.dayClinicDiariaGlosada", target = "diarias.dayClinicDiariaGlosada"),
			@Mapping(source = "resumo.dayClinicValorApresentado", target = "diarias.dayClinicValorApresentado"),
			@Mapping(source = "resumo.dayClinicValorGlosado", target = "diarias.dayClinicValorGlosado"),
			@Mapping(source = "resumo.acompanhanteDiariaCobrada", target = "diarias.acompanhanteDiariaCobrada"),
			@Mapping(source = "resumo.acompanhanteDiariaGlosada", target = "diarias.acompanhanteDiariaGlosada"),
			@Mapping(source = "resumo.acompanhanteValorApresentado", target = "diarias.acompanhanteValorApresentado"),
			@Mapping(source = "resumo.acompanhanteValorGlosado", target = "diarias.acompanhanteValorGlosado"),
			@Mapping(source = "resumo.isolamento", target = "diarias.isolamento"),
			@Mapping(source = "resumo.observacao", target = "diarias.observacao"),
			@Mapping(expression = "java(capeanteInternacao.getResumo().valorTotalApresentado())", target = "valorApresentado"),
			@Mapping(expression = "java(capeanteInternacao.getResumo().valorTotalGlosado())", target = "valorGlosado"),
			@Mapping(expression = "java(capeanteInternacao.getResumo().valorTotalLiberado())", target = "valorLiberado")
	})
	RestCapeanteInternacao convertModelToRest(CapeanteInternacao capeanteInternacao);
	
	@Mappings(value = {
			@Mapping(target = "prontuario.numero", source = "idProntuario"),
			@Mapping(target = "prontuario.paciente.nome", source = "paciente"),
			@Mapping(target = "prontuario.paciente.matricula", source = "matricula"),
			@Mapping(target = "prontuario.hospital.hospital.razaoSocial", source = "hospital"),
			@Mapping(target = "prontuario.paciente.cliente.razaoSocial", source = "cliente"),
			@Mapping(target = "prontuario.senhaInternacao", source = "senhaInternacao"),
			@Mapping(target = "internado.inicioCobranca", source = "inicioCobranca"),
			@Mapping(target = "internado.status.descricao", source = "status"),
			@Mapping(target = "internado.medico.nome", source = "nomeMedico"),
			@Mapping(target = "internado.enfermeiro.nome", source = "nomeEnfermeiro"),
			@Mapping(target = "internado.observacao", source = "observacao")

	})
	RestCapeanteInternacao convertProjectionToRest(CapeanteInternacaoProjection projection);
}
