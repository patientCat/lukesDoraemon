package org.luke.doraemon.common.error;

public enum PrimaryErrorCode {
    AuthFailure("AuthFailure"),
    FailedOperation("FailedOperation"),
    InternalError("InternalError"),
    InvalidParameter("InvalidParameter"),
    LimitExceeded("LimitExceeded"),
    MissingParameter("MissingParameter"),
    OperationDenied("OperationDenied"),
    RequestLimitExceeded("RequestLimitExceeded"),
    ResourceInUse("ResourceInUse"),
    ResourceInsufficient("ResourceInsufficient"),
    ResourceNotFound("ResourceNotFound"),
    ResourceUnavailable("ResourceUnavailable"),
    ResourcesSoldOut("ResourcesSoldOut"),
    UnauthorizedOperation("UnauthorizedOperation"),
    UnsupportedOperation("UnsupportedOperation"),
    ;
    PrimaryErrorCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    private final String code;
}

