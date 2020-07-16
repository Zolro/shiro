package com.zyd.shiro.utils;

import lombok.Data;

@Data
public class Calibration {
    private String name;
    private boolean intervals;//是否有区间状态 true是 false否
    private boolean interType;//区间类型 true日期 false数值
    private String startVal;
    private String endVal;
    private String value;
    private int index;//在当前表格中 指定标题的下标索引
}
