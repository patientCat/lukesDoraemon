package org.luke.doraemon.common.error;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

public class ErrorCodeUtils {
    public static final char STRING_DOT = '.';

    public static String getFirstCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return code;
        }
        if (isFirstCode(code)) {
            return code;
        }
        int i = StringUtils.indexOf(code, STRING_DOT);
        return StringUtils.substring(code, 0, i);
    }

    public static boolean isFirstCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return false;
        }
        int i = StringUtils.indexOf(code, STRING_DOT);
        return i == -1;
    }

    public static Pair<String, String> splitErrorCode(String errorCode) {
        String[] result = StringUtils.split(errorCode, STRING_DOT);
        if (result.length == 0) {
            return Pair.of("", "");
        }
        if (result.length == 1) {
            return Pair.of(result[0], "");
        } else {
            return Pair.of(result[0], result[1]);
        }

    }
}

