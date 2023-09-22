package com.bmais.gestao.restapi.model.converter;

import java.sql.Timestamp;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Timestamp>
{
	@Override
	public Timestamp convertToDatabaseColumn (LocalDate attribute)
	{
		return attribute == null ? null : Timestamp.valueOf(attribute.atStartOfDay());
	}

	@Override
	public LocalDate convertToEntityAttribute (Timestamp dbData)
	{
		return dbData == null ? null : dbData.toLocalDateTime().toLocalDate();
	}
}
