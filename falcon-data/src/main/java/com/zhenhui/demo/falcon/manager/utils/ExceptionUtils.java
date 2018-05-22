package com.zhenhui.demo.falcon.manager.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import org.mybatis.spring.MyBatisSystemException;
import org.springframework.dao.DuplicateKeyException;

public class ExceptionUtils {

    private static final String DUPLICATION_ENTRY_STRING = "Duplicate entry";

    public static String getStackTrace(Throwable aThrowable) {
        if (null != aThrowable) {
            final Writer result = new StringWriter();
            final PrintWriter printWriter = new PrintWriter(result);
            aThrowable.printStackTrace(printWriter);
            return result.toString().replaceAll("\\n", "-");
        }

        return "";
    }

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
