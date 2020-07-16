package com.zyd.shiro.service;

import com.zyd.shiro.entity.ArcTitle;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * @Classname WxUserService
 * @Description TODO
 * @Date 2020/6/13 22:07
 * @Created by King
 */
public interface ArcTitleService {
    void save(ArcTitle arcTitle);
    ArcTitle creTitle(Workbook work, long direId);
    ArcTitle findAllByFileDire(long fileDire);

    ArcTitle selectOnByDire(long id);

    ArcTitle selectOnById(long id);
}
