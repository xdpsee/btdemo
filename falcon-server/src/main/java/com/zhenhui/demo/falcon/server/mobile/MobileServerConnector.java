package com.zhenhui.demo.falcon.server.mobile;

import com.zhenhui.demo.falcon.core.support.AbstractServerConnector;
import com.zhenhui.demo.falcon.core.support.Context;
import com.zhenhui.demo.falcon.core.support.handler.DefaultDataHandler;
import com.zhenhui.demo.falcon.core.support.handler.filter.DefaultDataFilter;
import com.zhenhui.demo.falcon.server.mobile.decoder.MobileFrameDecoder;
import com.zhenhui.demo.falcon.server.mobile.decoder.MobileProtocolDecoder;
import com.zhenhui.demo.falcon.server.mobile.handler.RegistryMessageHandler;
import io.netty.channel.ChannelPipeline;

public class MobileServerConnector extends AbstractServerConnector {

    public MobileServerConnector(Context context) {
        super(context);
    }

    @Override
    public void initPipeline(ChannelPipeline pipeline) {
        pipeline.addLast("frameDecoder", new MobileFrameDecoder());
        pipeline.addLast("objectDecoder", new MobileProtocolDecoder());

        pipeline.addLast("loginHandler", new RegistryMessageHandler(context));
        pipeline.addLast("filterHandler", new DefaultDataFilter(context, new MobileFilterPolicy(context)));
        pipeline.addLast("dataHandler", new DefaultDataHandler(context));

    }
}
