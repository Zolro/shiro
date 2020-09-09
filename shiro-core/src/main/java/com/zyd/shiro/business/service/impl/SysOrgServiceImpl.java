/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.shiro.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.shiro.business.entity.Org;
import com.zyd.shiro.business.entity.User;
import com.zyd.shiro.business.enums.UserStatusEnum;
import com.zyd.shiro.business.service.SysOrgService;
import com.zyd.shiro.business.service.SysRoleService;
import com.zyd.shiro.business.service.SysUserService;
import com.zyd.shiro.business.vo.OrgConditionVO;
import com.zyd.shiro.business.vo.OrgSimpleVO;
import com.zyd.shiro.business.vo.UserConditionVO;
import com.zyd.shiro.framework.exception.ZhydException;
import com.zyd.shiro.framework.holder.RequestHolder;
import com.zyd.shiro.persistence.beans.SysOrg;
import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.persistence.mapper.SysOrgMapper;
import com.zyd.shiro.persistence.mapper.SysUserMapper;
import com.zyd.shiro.util.IpUtil;
import com.zyd.shiro.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class SysOrgServiceImpl implements SysOrgService {

    @Autowired
    private SysOrgMapper sysOrgMapper;



    @Override
    public Org insert(Org org) {
        Assert.notNull(org, "机构不可为空！");
        org.setUpdateTime(new Date());
        org.setCreateTime(new Date());
        sysOrgMapper.insertSelective(org.getSysOrg());
        return org;
    }

    @Override
    public void insertList(List<Org> orgs) {

    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {

        return sysOrgMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Org org) {
        Assert.notNull(org, "机构不可为空！");
        org.setUpdateTime(new Date());
        return sysOrgMapper.updateByPrimaryKey(org.getSysOrg()) > 0;
    }

    @Override
    public boolean updateSelective(Org org) {
        Assert.notNull(org, "机构不可为空！");
        org.setUpdateTime(new Date());
        return sysOrgMapper.updateByPrimaryKeySelective(org.getSysOrg()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */

    @Override
    public Org getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysOrg sysOrg = sysOrgMapper.selectByPrimaryKey(primaryKey);
        return null == sysOrg ? null : new Org(sysOrg);
    }


    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param org
     * @return
     */
    @Override
    public Org getOneByEntity(Org org) {
        Assert.notNull(org, "机构不可为空！");
        SysOrg sysOrg = sysOrgMapper.selectOne(org.getSysOrg());
        return null == sysOrg ? null : new Org(sysOrg);
    }

    @Override
    public List<Org> listAll() {
        List<SysOrg> sysOrgs = sysOrgMapper.selectAll();

        if (CollectionUtils.isEmpty(sysOrgs)) {
            return null;
        }
        List<Org> orgs = new ArrayList<>();
        for (SysOrg sysOrg : sysOrgs) {
            orgs.add(new Org(sysOrg));
        }
        return orgs;
    }


    @Override
    public List<Org> listByEntity(Org org) {
        Assert.notNull(org, "机构不可为空！");
        List<SysOrg> sysOrgs = sysOrgMapper.select(org.getSysOrg());
        if (CollectionUtils.isEmpty(sysOrgs)) {
            return null;
        }
        List<Org> orgs = new ArrayList<>();
        for (SysOrg or : sysOrgs) {
            orgs.add(new Org(or));
        }
        return orgs;
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Org> findPageBreakByCondition(OrgConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysOrg> sysOrgs = sysOrgMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysOrgs)) {
            return null;
        }
        List<Org> orgs = new ArrayList<>();
        for (SysOrg or : sysOrgs) {
            orgs.add(new Org(or));
        }
        PageInfo bean = new PageInfo<SysOrg>(sysOrgs);
        bean.setList(orgs);
        return bean;
    }


    /**
     * 根据用户名查找
     *
     * @param code
     * @return
     */
    @Override
    public Org getByCode(String code) {
        Org org = new Org(code);
        return getOneByEntity(org);
    }

    @Override
    public List<OrgSimpleVO> simplelist() {
        return sysOrgMapper.simplelist();
    }


}
