package com.zhenhui.demo.falcon.service.utils;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;

public class ExceptionUtils extends com.zhenhui.demo.falcon.core.support.utils.ExceptionUtils {

    private static final String DUPLICATION_ENTRY_STRING = "Duplicate entry";

    public static boolean hasDuplicateEntryException(Exception e) {

        if (e instanceof DuplicateKeyException
            || (e instanceof MyBatisSystemException && e.getMessage().contains(DUPLICATION_ENTRY_STRING))) {
            return true;
        }

        if (e.getCause() != null && e.getCause() instanceof MyBatisSystemException
            && e.getCause().getMessage().contains(DUPLICATION_ENTRY_STRING)) {
            return true;
        }

        return e.getCause() != null
            && e.getCause().getMessage().contains(DUPLICATION_ENTRY_STRING);

    }

}
