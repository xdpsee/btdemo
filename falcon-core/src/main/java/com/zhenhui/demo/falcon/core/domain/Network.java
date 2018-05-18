package com.zhenhui.demo.falcon.core.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Network {
    
    public Network(CellTower cellTower) {
        cellTowers.add(cellTower);
    }

    private Integer mobileCountryCode;

    private Integer mobileNetworkCode;

    private String radioType = "gsm";

    private String carrier;

    private Boolean considerIp = false;

    private final List<CellTower> cellTowers = new ArrayList<>();

    private final List<WifiAccessPoint> wifiAccessPoints = new ArrayList<>();



}
