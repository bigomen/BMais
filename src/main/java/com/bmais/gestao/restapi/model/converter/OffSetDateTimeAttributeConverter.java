package com.bmais.gestao.restapi.model.converter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class OffSetDateTimeAttributeConverter implements AttributeConverter<OffsetDateTime, Timestamp>
{
	@Override
	public Timestamp convertToDatabaseColumn (OffsetDateTime attribute)
	{
		if(attribute == null)
		{
			return null;
		}

		Instant instant = attribute.toInstant();
		return attribute == null ? null : Timestamp.from(instant);
	}

	@Override
	public OffsetDateTime convertToEntityAttribute (Timestamp dbData)
	{
		if(dbData == null)
		{
			return null;
		}

		Instant instant = Instant.ofEpochMilli(dbData.getTime());
		return dbData == null ? null : OffsetDateTime.ofInstant(instant, ZoneOffset.UTC);
	}
}
