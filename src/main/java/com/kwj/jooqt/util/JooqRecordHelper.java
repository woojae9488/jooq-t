package com.kwj.jooqt.util;

import com.kwj.jooqt.exception.RecordNotFoundException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class JooqRecordHelper {

    public static <K, V> Map.Entry<K, V> firstEntry(Map<K, V> map, Object... params) {
        return map.entrySet()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RecordNotFoundException(paramsToString(params)));
    }

    private static String paramsToString(Object... params) {
        return ArrayUtils.isNotEmpty(params) ?
                "(" + StringUtils.join(params, ", ") + ")" :
                "(Has no params)";
    }

}
