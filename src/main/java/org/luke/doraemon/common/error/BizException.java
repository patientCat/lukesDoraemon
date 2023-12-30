package org.luke.doraemon.common.error;

import lombok.Getter;

@Getter
public class BizException extends RuntimeException{
    private final Exception externalException;
    private final IBizErrorCode bizErrorCode;
    private final Object[] args;

    public BizException(IBizErrorCode IBizErrorCode, Object... args){
        this(null, IBizErrorCode, args);
    }

    public BizException(Exception externalException, IBizErrorCode bizErrorCode, Object... args){
        this.externalException = externalException;
        this.bizErrorCode = bizErrorCode;
        this.args = args;
    }
    @Override
    public String getMessage() {
        return this.bizErrorCode.getErrorMessage(args);
    }

    public String getCode() {
        return bizErrorCode.getErrorCode();
    }

    public PrimaryErrorCode getPrimaryErrorCode(){
        return bizErrorCode.getPrimaryCode();
    }

    public String getDownStreamMessage(){
        return bizErrorCode.getDownStreamErrorMessage(args);
    }

    public String getDownStreamErrorCode(){
        return bizErrorCode.getDownStreamErrorCode(args);
    }

    public String getDownStreamPrimaryErrorCode(){
        String downStreamCode = getDownStreamErrorCode();
        return bizErrorCode.getDownStreamPrimaryCode(downStreamCode);
    }
}
