package com.zhenhui.demo.falcon.core.domain;

import lombok.Data;

@Data
public class CellTower {

    public static CellTower from(int mcc, int mnc, int lac, long cid) {
        CellTower cellTower = new CellTower();
        cellTower.setMobileCountryCode(mcc);
        cellTower.setMobileNetworkCode(mnc);
        cellTower.setLocationAreaCode(lac);
        cellTower.setCellId(cid);
        return cellTower;
    }

    public static CellTower from(int mcc, int mnc, int lac, long cid, int rssi) {
        CellTower cellTower = CellTower.from(mcc, mnc, lac, cid);
        cellTower.setSignalStrength(rssi);
        return cellTower;
    }

    private String radioType;

    private Long cellId;

    private Integer locationAreaCode;

    private Integer mobileCountryCode;

    private Integer mobileNetworkCode;

    private Integer signalStrength;

}
