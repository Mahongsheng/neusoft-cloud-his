package com.neu.his.util;

import lombok.Data;

@Data
public class ReturnState {
    /**
     * 501：用户名不存在
     * 502：用户密码错误
     * 503：挂号成功
     * 504：挂号失败
     * 505：病历首页填写成功
     * 506：病历首页填写失败
     * 507：退号成功
     * 508：退号失败
     * 509：开药成功
     * 510：开药失败
     *
     */
    private Integer state;
    private String detail;

}
