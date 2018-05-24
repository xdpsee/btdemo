package com.zhenhui.demo.falcon.server.mobile.encoder;

import com.zhenhui.demo.falcon.core.domain.Command;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.support.encoder.StringProtocolEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MobileProtocolEncoder extends StringProtocolEncoder {

    private static final Logger logger = LoggerFactory.getLogger(MobileProtocolEncoder.class);

    @Override
    protected byte[] encodeCommand(Command command) throws Exception {
        switch (command.getType()) {
            case TYPE_CUSTOM:
                return formatCommand(command, "##%s#", Command.KEY_DATA);
            default:
                logger.warn(String.format("unsupported command type: %s", command.getType()));
                break;
        }

        return null;
    }

    @Override
    protected String encodeDeviceId(UniqueID deviceId) {
        return deviceId.getValue();
    }
}


