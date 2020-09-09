package com.zyd.shiro.entity;

import com.zyd.shiro.business.annotation.FieldValue;
import com.zyd.shiro.business.enums.FieldTypeEnum;
import com.zyd.shiro.framework.object.AbstractDataDO;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = false)
public class ArcFile extends AbstractDataDO {//档案数据

    @FieldValue(name="档号",type = FieldTypeEnum.VALUE)
    private String fileNumber;

    @FieldValue(name="分类名称",type = FieldTypeEnum.VALUE)
    private String categoryName;

    @FieldValue(name="档案类别",type = FieldTypeEnum.VALUE)
    private String fileCategory;

    @FieldValue(name="全宗名称",type = FieldTypeEnum.VALUE)
    private String fondName;

    @FieldValue(name="保管期限代码",type = FieldTypeEnum.VALUE)
    private String storagePeriodCode;

    @FieldValue(name="密级",type = FieldTypeEnum.VALUE)
    private String secretLevel;

    @FieldValue(name="文件标题",type = FieldTypeEnum.VALUE)
    private String fileTitle;

    @FieldValue(name="组织机构",type = FieldTypeEnum.VALUE)
    private String organization;

    @FieldValue(name="备注",type = FieldTypeEnum.VALUE)
    private String remarks;

    @FieldValue(name="存放位置",type = FieldTypeEnum.VALUE)
    private String storageLocation;

    @FieldValue(name="登记号",type = FieldTypeEnum.VALUE)
    private String licenseNumber;

    @FieldValue(name="载体数量",type = FieldTypeEnum.VALUE)
    private String carriersNumber;

    @FieldValue(name="全宗号",type = FieldTypeEnum.VALUE)
    private String fondNumber;

    @FieldValue(name="案卷号",type = FieldTypeEnum.VALUE)
    private String filesNumber;

    @FieldValue(name="盒号",type = FieldTypeEnum.VALUE)
    private String boxNumber;

    @FieldValue(name="责任者",type = FieldTypeEnum.VALUE)
    private String person;

    @FieldValue(name="附件",type = FieldTypeEnum.VALUE)
    private String annex;

    @FieldValue(name="张页号",type = FieldTypeEnum.VALUE)
    private String pageNumber;

    @FieldValue(name="问题",type = FieldTypeEnum.VALUE)
    private String problem;

    @FieldValue(name="保管期限",type = FieldTypeEnum.VALUE)
    private String storageTime;

    @FieldValue(name="主题词",type = FieldTypeEnum.VALUE)
    private String subjectHeading;

    @FieldValue(name="附注",type = FieldTypeEnum.VALUE)
    private String note;

    @FieldValue(name="年度",type = FieldTypeEnum.VALUE)
    private String year;

    @FieldValue(name="件号",type = FieldTypeEnum.VALUE)
    private String partNumber;

    @FieldValue(name="卷内顺序号",type = FieldTypeEnum.VALUE)
    private int volumeNumber;

    @FieldValue(name="分类号",type = FieldTypeEnum.VALUE)
    private String categoryNumber;

    @FieldValue(name="成文日期",type = FieldTypeEnum.VALUE)
    private String writingDate;

    @FieldValue(name="页数",type = FieldTypeEnum.VALUE)
    private String pagesNumber;

    @FieldValue(name="目录号",type = FieldTypeEnum.VALUE)
    private String catalogNumber;

    @FieldValue(name="文件路径",type = FieldTypeEnum.VALUE)
    private String filePath;

    @FieldValue(name="是否包含文件",type = FieldTypeEnum.VALUE)
    private Integer including;

    @FieldValue(name="所属目录",type = FieldTypeEnum.VALUE)
    private Long direId;

    @FieldValue(name="类型",type = FieldTypeEnum.VALUE)
    private Integer type;//0:文件类型 1：案卷类型

    @FieldValue(name="文件级档号",type = FieldTypeEnum.VALUE)
    private String levelNumber;


}
