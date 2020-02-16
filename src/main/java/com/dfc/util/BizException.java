package com.dfc.util;

import java.io.Serializable;
import java.util.Map;
import org.slf4j.helpers.MessageFormatter;

public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private Object errorData;

    public BizException() {
    }

    public BizException(String errorMsg) {
        super(errorMsg);
    }

    public BizException(String format, String... argArray) {
        super(MessageFormatter.arrayFormat(format, argArray).getMessage());
    }

    public BizException(Object errorMsg) {
        super(errorMsg != null ? errorMsg.toString() : "");
    }

    public BizException(String errorMsg, Map<String, Object> errorMap) {
        super(errorMsg);
        this.errorData = errorMap;
    }

    public BizException(RuntimeException e) {
        super(e);
    }

    public BizException(Throwable e) {
        super(e);
    }

    public Object getErrorData() {
        return this.errorData;
    }
}
