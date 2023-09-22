package com.bmais.gestao.restapi.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class UtilData
{
	public static LocalDateTime obterDataHoraAtual()
	{
		return LocalDateTime.now();
	}
	
	public static OffsetDateTime obterDataHoraAtualOffSet()
	{
		return OffsetDateTime.now();
	}
	
	public static LocalDate obterDataAtual()
	{
		return LocalDate.now();
	}
	
	public static boolean dataMaiorIgualADataAtual(LocalDate localDate)
	{
		int compareTo = localDate.compareTo(obterDataAtual());
		
		if(compareTo < 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static boolean dataMenorIgualADataAtual(LocalDate localDate)
	{
		int compareTo = localDate.compareTo(obterDataAtual());
		
		if(compareTo > 0)
		{
			return false;
		}
		
		return true;
	}
	
	public static String diaDaSemana(LocalDate localDate)
	{
		return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));
	}
	
	public static DateTimeFormatter formatter(String pattern)
	{
		return DateTimeFormatter.ofPattern(pattern);
	}
	
	public static String formataData(LocalDate data, String pattern)
	{
		DateTimeFormatter formatter = formatter(pattern);
		return data.format(formatter);
	}
}
