package com.zhenhui.demo.falcon.core.support.encoder;

import java.util.ArrayList;
import java.util.List;

import com.zhenhui.demo.falcon.core.domain.Command;

public abstract class StringProtocolEncoder extends AbstractProtocolEncoder {

    public interface ValueFormatter {
        String formatValue(String key, Object value);
    }

    protected byte[] formatCommand(Command command, String format, ValueFormatter valueFormatter, String... keys) throws Exception {

        List<Object> values = new ArrayList<>();
        for (String key : keys) {
            if (valueFormatter != null) {
                values.add(valueFormatter.formatValue(key, command.getAttributes().get(key)));
            } else {
                values.add(command.getAttributes().get(key));
            }
        }

        return String.format(format, values.toArray()).getBytes("UTF-8");
    }

    protected byte[] formatCommand(Command command, String format, String... keys) throws Exception {
        return formatCommand(command, format, null, keys);
    }

}
