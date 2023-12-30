package test.org.doraemon.common.error;

import java.text.MessageFormat;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.luke.doraemon.common.error.ErrorCodeUtils;
import org.luke.doraemon.common.error.IBizErrorCode;
import org.luke.doraemon.common.error.PrimaryErrorCode;

public enum TestErrorCode implements IBizErrorCode {
    TestBaseError(PrimaryErrorCode.InternalError, "TestBaseError", "this is a message, reason = {0}", "{1}", "{2}");
    private final PrimaryErrorCode primaryErrorCode;
    private final String detailErrorCode;
    private final String errorMessage;
    private final String downStreamErrorCode;
    private final String downStreamErrorMessage;

    TestErrorCode(PrimaryErrorCode primaryErrorCode, String detailErrorCode, String errorMessage,
            String downStreamErrorCode, String downStreamErrorMessage) {
        this.primaryErrorCode = primaryErrorCode;
        this.detailErrorCode = detailErrorCode;
        this.errorMessage = errorMessage;
        this.downStreamErrorCode = downStreamErrorCode;
        this.downStreamErrorMessage = downStreamErrorMessage;
    }

    @Override
    public String getErrorMessage(Object... args) {
        return new MessageFormat(this.errorMessage).format(args);
    }

    @Override
    public String getErrorCode() {
        Objects.requireNonNull(getPrimaryCode(), "primaryCode can't be null");
        String code = getPrimaryCode().getCode();
        if (StringUtils.isBlank(detailErrorCode())) {
            return code;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(code)
                    .append(".")
                    .append(detailErrorCode());
            return stringBuffer.toString();
        }
    }

    @Override
    public PrimaryErrorCode getPrimaryCode() {
        return this.primaryErrorCode;
    }

    @Override
    public String detailErrorCode() {
        return this.detailErrorCode;
    }

    @Override
    public String getDownStreamErrorMessage(Object... args) {
        return replacePlaceholderWithValue(this.downStreamErrorMessage, args);
    }

    @Override
    public String getDownStreamErrorCode(Object... args) {
        return replacePlaceholderWithValue(this.downStreamErrorCode, args);
    }

    @Override
    public String getDownStreamPrimaryCode(String downStreamErrorCode) {
        return ErrorCodeUtils.getFirstCode(downStreamErrorCode);
    }
}
