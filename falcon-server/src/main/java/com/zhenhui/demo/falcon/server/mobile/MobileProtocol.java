package com.zhenhui.demo.falcon.server.mobile;

import java.util.Collection;

import com.zhenhui.demo.falcon.core.domain.CommandType;
import com.zhenhui.demo.falcon.core.support.AbstractProtocol;
import com.zhenhui.demo.falcon.core.support.Context;

@SuppressWarnings("unused")
public class MobileProtocol extends AbstractProtocol {

    public MobileProtocol(Context context) {
        super(context,"mobile");
    }

    @Override
    public Collection<CommandType> getSupportedCommands() {

        Collection<CommandType> types = super.getSupportedCommands();
        types.add(CommandType.TYPE_MODE_POWER_SAVING);

        return types;
    }
}

