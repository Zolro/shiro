package com.zyd.shiro.entity;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data//file4 字段作为文件唯一索引 即文件级档号,    当类型是 案卷类型时 file3文档级档号   file5年度
@EqualsAndHashCode(callSuper = false)
public class ArcData extends AbstractDO {//文件
    private String file0;
    private String file1;
    private String file2;
    private String file3;//文档级唯一号码
    private String file4;//文件级唯一号码
    private String file5;
    private String file6;
    private String file7;
    private String file8;
    private String file9;
    private String file10;
    private String file11;
    private String file12;
    private String file13;
    private String file14;
    private String file15;
    private String file16;
    private String file17;
    private String file18;
    private String file19;
    private String file20;
    private String file21;
    private String file22;
    private String file23;
    private String file24;
    private String file25;
    private String file26;
    private String file27;
    private String file28;
    private String file29;
    private String file30;
    private String file31;
    private String file32;
    private String file33;
    private String file34;
    private String file35;
    private String file36;
    private String file37;
    private String file38;
    private String file39;
    private String file40;
    private String file41;
    private String file42;
    private String file43;
    private String file44;
    private String file45;
    private String file46;
    private String file47;
    private String file48;
    private String file49;
    private String file50;
    private String file51;
    private String file52;
    private String file53;
    private String file54;
    private String file55;
    private String file56;
    private String file57;
    private String file58;
    private String file59;
    private String file60;
    private boolean openState;//0 正常 1隐藏
    private String filePath;
    private long titleId;//对应标题目录
    private long parDataId;
    private String searchString;

}
