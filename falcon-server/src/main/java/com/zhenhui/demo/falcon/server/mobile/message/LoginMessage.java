package com.zhenhui.demo.falcon.server.mobile.message;

import com.zhenhui.demo.falcon.core.domain.Message;
import com.zhenhui.demo.falcon.core.domain.UniqueID;
import com.zhenhui.demo.falcon.core.domain.UniqueID.Type;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginMessage implements Message {
    @NonNull
    private double protocolVersion;
    @NonNull
    private String imei;
    @NonNull
    private double appVersion;
    @NonNull
    private String time;
    @NonNull
    private String body;// raw data

    @Override
    public UniqueID deviceId() {
        return new UniqueID(Type.IMEI, imei);
    }

    @Override
    public byte[] rawBytes() {
        return body.getBytes();
    }

}
