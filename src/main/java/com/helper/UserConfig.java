package com.helper;

import cn.hutool.setting.Setting;
import cn.hutool.setting.SettingUtil;

import java.util.*;

/**
 * 用户信息
 * 通过小程序抓包购物车接口获取headers和body中的数据填入
 */
public class UserConfig {

    private static final Setting setting = SettingUtil.get("config");

    private static final Map<String, String> headers = new HashMap<>();

    //此处不加泛型是为了兼容params和body的泛型不同
    private static final Map body = new HashMap<>();

    private static final Map<String, String> paramsBody;

    private static final Map<String, Object> formBody;

    //城市
    private static final String cityId = "0101";//默认上海

    static {
        headers.put("ddmc-city-number", cityId);
        headers.put("ddmc-build-version", "2.83.0");
        headers.put("ddmc-station-id", setting.getStr("ddmc-station-id"));
        headers.put("ddmc-channel", "applet");
        headers.put("ddmc-os-version", "[object Undefined]");
        headers.put("ddmc-app-client-id", "4");
        headers.put("ddmc-ip", "");
        headers.put("ddmc-api-version", "9.50.0");
        headers.put("accept-encoding", "gzip,compress,br,deflate");
        headers.put("referer", "https://servicewechat.com/wx1e113254eda17715/425/page-frame.html");
        headers.put("ddmc-device-id", setting.getStr("ddmc-device-id"));
        headers.put("cookie", setting.getStr("cookie"));
        headers.put("ddmc-longitude", setting.getStr("ddmc-longitude"));
        headers.put("ddmc-latitude", setting.getStr("ddmc-latitude"));
        headers.put("ddmc-uid", setting.getStr("ddmc-uid"));
        headers.put("user-agent", setting.getStr("user-agent"));

        body.put("uid", headers.get("ddmc-uid"));
        body.put("longitude", headers.get("ddmc-longitude"));
        body.put("latitude ", headers.get("ddmc-latitude"));
        body.put("station_id", headers.get("ddmc-station-id"));
        body.put("city_number", headers.get("ddmc-city-number"));
        body.put("api_version", headers.get("ddmc-api-version"));
        body.put("app_version ", headers.get("ddmc-build-version"));
        body.put("applet_source", "");
        body.put("channel", "applet");
        body.put("app_client_id", "4");
        body.put("sharer_uid", "");
        body.put("h5_source", "");
        body.put("time", headers.get("ddmc-time"));
        body.put("s_id", headers.get("s_id"));
        //device-id和openid相同
        body.put("openid", setting.getStr("ddmc-device-id"));
        body.put("device_token", setting.getStr("device_token"));

        paramsBody = body;
        formBody = body;
    }

    /**
     * 确认收货地址id和站点id
     * 每天抢之前先允许一下此接口 确认登录信息是否有效 如果失效了重新抓一次包
     */
    public static void main(String[] args) {
        Api.checkUserConfig();
    }

    public static Map<String, String> getHeaders() {
        headers.put("ddmc-time", String.valueOf(new Date().getTime() / 1000));
        return headers;
    }


    public static Map<String, String> getBody() {
        return body;
    }

    /**
     * params接口 泛型要求<String,String>
     *
     * @return paramsBody
     */
    public static Map<String, String> getParamsBody() {
        return paramsBody;
    }

    /**
     * form表单 泛型要求<String,Object>
     *
     * @return formBody
     */
    public static Map<String, Object> getFormBody() {
        return formBody;
    }

    public static String get(String key) {
        return setting.get(key);
    }

    public static void set(String key, String value) {
        setting.put(key, value);
    }

}
