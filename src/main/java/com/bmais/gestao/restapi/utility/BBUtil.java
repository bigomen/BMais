package com.bmais.gestao.restapi.utility;

import java.util.Collection;

public class BBUtil
{
    public static boolean isVazio(Collection<?> collection)
    {
        return (collection == null || collection.isEmpty());
    }

    public static boolean temElementos(Collection<?> collection)
    {
        return (collection != null && !collection.isEmpty());
    }
}
