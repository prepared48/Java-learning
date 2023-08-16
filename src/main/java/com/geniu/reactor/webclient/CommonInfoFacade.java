package com.geniu.reactor.webclient;

import com.qunar.mobile.mpub.constant.ClientType;
import com.qunar.mobile.mpub.model.AppProductEnum;

/**
 * C参信息
 */
public interface CommonInfoFacade {

    /**
     * 是否客户端
     */
    boolean isClient();

    /**
     * 获取客户端类型
     */
    ClientType getClientType();

    /**
     * 获取产品类型
     */
    AppProductEnum getAppProduct();

    /**
     * 是否酒店独立客户端
     */
    boolean isHotel();

    /**
     * 是否是Android酒店独立客户端
     */
    boolean isHotelAndroid();

    /**
     * 是否是iPhone酒店独立客户端
     */
    boolean isHotelIPhone();

    /**
     * 是否是大客户端free版本
     */
    boolean isTravelFree();

    /**
     * 是否是大客户端pro版本
     */
    boolean isTravelPro();

    /**
     * 是否是大客户端生活版本
     */
    boolean isLife();

    /**
     * 是否是大客户端特卖版本
     */
    boolean isDeals();

    /**
     * 是否是大客户端
     */
    boolean isTravel();

    boolean isSchoolApp();

    /**
     * 推广运营的马甲客户端包
     */
    boolean isMajiaBao();

    /**
     * 火车票独立客户端
     */
    boolean isTrainApp();

    /**
     * 是否是Android大客户端
     */
    boolean isTravelAndroid();

    /**
     * 是否是iPhone大客户端
     */
    boolean isTravelIPhone();

    /**
     * 获取客户端真实IP
     */
    String getClientIp();

    String getSource();

    String getCid();

    String getUid();

    String getGid();

    String getPid();

    String getVid();

    String getAid();

    String getMac();

    String getModel();

    String getOsVersion();

    String getSid();

    String getT();

    String getIp();

    String getAdid();

    String getIid();

    String getUn();

    String getUsid();

    String getNt();

    String getMno();

    String getPort();

    String getRef();

    String getBrush();

    String getLat();

    String getLgt();

    String getTraceId();

    String getCatom();

    String getHotelCatomVersion();

    int getCatomVersion();

    String getCatomType();

    int getRnVersion();

    int getVersionId();

    String getCqp();

    /**
     * 原始C 参数
     *
     * @return
     */
    String getOriginCParam();

    boolean isAndroid();

    boolean isIPhone();

    boolean isIPad();

    boolean hasCqp();

    boolean isVersionGET(int version);

    boolean isVersionLT(final int vidPostFix);

    boolean isVersionGT(int version);

    boolean isCatomVersionGET(int version);

    boolean isRnVersionGET(final int rnVersion);

    boolean isTouch();

    default boolean isIphonePro() {
        return this.isIPhone() && this.isTravelPro();
    }

    boolean isSmartApp();

    int getBizVersion();

    //是否是PC端
    boolean isWebsite();

    String getFp();

    default boolean isApp() {
        return this.isIPhone() || this.isAndroid();
    }

}
