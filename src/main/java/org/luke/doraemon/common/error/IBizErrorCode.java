package org.luke.doraemon.common.error;

import java.text.MessageFormat;
import java.util.regex.Pattern;

public interface IBizErrorCode {

    String getErrorMessage(Object... args);
    String getErrorCode();
    PrimaryErrorCode getPrimaryCode();
    String detailErrorCode();

    String getDownStreamErrorMessage(Object... args);
    String getDownStreamErrorCode(Object... args);
    String getDownStreamPrimaryCode(String downStreamErrorCode);

    Pattern errorPlaceholderPattern = Pattern.compile("\\{\\d+\\}");

    // 用来过滤非法的{0}例子
    default String replacePlaceholderWithValue(String origin, Object...args) {
        if (origin == null) {
            return null;
        }
        String formattedErrorAttribute = new MessageFormat(origin).format(args);
        if (errorPlaceholderPattern.matcher(formattedErrorAttribute).matches()) {
            return null;
        } else if (formattedErrorAttribute.equals("null")) {
            return null;
        }
        return formattedErrorAttribute;
    }
}

