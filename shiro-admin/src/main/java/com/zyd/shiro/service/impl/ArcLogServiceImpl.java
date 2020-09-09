package com.zyd.shiro.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.entity.ArcLog;
import com.zyd.shiro.mapper.ArcLogMapper;
import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.persistence.mapper.SysUserMapper;
import com.zyd.shiro.service.ArcLogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Classname WxUserServiceImpl
 * @Description TODO
 * @Date 2020/6/13 22:10
 * @Created by King
 */
@Service
public class ArcLogServiceImpl implements ArcLogService {

    @Resource
    private ArcLogMapper arcLogMapper;
    @Resource
    private SysUserMapper userMapper;


    @Override
    public PageInfo<ArcLog> findAll(Integer pageNum,Integer pageSize,String param) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArcLog> arcLogs = arcLogMapper.findPageBreakByCondition(param);
        return new PageInfo(arcLogs);
    }

    @Override
    public void save(ArcLog arcLog) {
        arcLogMapper.insertSelective(arcLog);
    }

    @Override
    public void generate(String action) {
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        SysUser user = userMapper.selectByPrimaryKey(userId);
        ArcLog log = new ArcLog();
        String content = "用户"+user.getUsername()+action;
        log.setContent(content);
        log.setAction(action);
        log.setUser(userId);
        log.setDept(user.getDept());
        log.setOrg(user.getOrg());
        save(log);

    }


}
