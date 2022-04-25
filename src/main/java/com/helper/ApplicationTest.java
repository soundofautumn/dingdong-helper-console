package com.helper;

import lombok.extern.java.Log;

import java.util.Map;

/**
 * 抢菜测试程序
 */
@Log
public class ApplicationTest {

    public static void main(String[] args) {
        ApplicationTest.run();
    }

    public static void run() {
        assert UserConfig.get("ddmc-address-id").length() != 0;
        // 此为单次执行模式  用于在非高峰期测试下单  也必须满足3个前提条件  1.有收货地址  2.购物车有商品 3.能选择配送信息
        Api.allCheck();
        Map<String, Object> cartMap = Api.getCart(false);
        if (cartMap == null) {
            return;
        }
        Map<String, Object> multiReserveTimeMap = Api.getMultiReserveTime(UserConfig.get("ddmc-address-id"), cartMap);
        if (multiReserveTimeMap == null) {
            return;
        }
        Map<String, Object> checkOrderMap = Api.getCheckOrder(UserConfig.get("ddmc-address-id"), cartMap, multiReserveTimeMap);
        if (checkOrderMap == null) {
            return;
        }
        Api.addNewOrder(UserConfig.get("ddmc-address-id"), cartMap, multiReserveTimeMap, checkOrderMap);
    }
}


