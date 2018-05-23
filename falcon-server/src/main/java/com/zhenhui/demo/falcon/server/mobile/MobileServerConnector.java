package com.zhenhui.demo.falcon.server.mobile;

import com.zhenhui.demo.falcon.core.domain.Protocol;
import com.zhenhui.demo.falcon.core.support.AbstractServerConnector;
import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.handler.DefaultDataHandler;
import com.zhenhui.demo.falcon.core.support.handler.filter.DefaultDataFilter;
import com.zhenhui.demo.falcon.server.mobile.decoder.MobileFrameDecoder;
import com.zhenhui.demo.falcon.server.mobile.decoder.MobileProtocolDecoder;
import com.zhenhui.demo.falcon.server.mobile.handler.LoginMessageHandler;
import io.netty.channel.ChannelPipeline;

public class MobileServerConnector extends AbstractServerConnector {

    public MobileServerConnector(Context context) {
        super(context);
    }

    @Override
    public Protocol getProtocol() {
        return new MobileProtocol(context);
    }

    @Override
    public void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("frameDecoder", new MobileFrameDecoder());
        pipeline.addLast("protocolDecoder", new MobileProtocolDecoder());

        pipeline.addLast("loginHandler", new LoginMessageHandler(this));
        pipeline.addLast("filterHandler", new DefaultDataFilter(this, null));
        pipeline.addLast("dataHandler", new DefaultDataHandler(this));
    }
}

