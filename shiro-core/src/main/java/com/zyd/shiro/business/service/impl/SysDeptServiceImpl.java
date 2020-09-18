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
import com.zyd.shiro.business.entity.Dept;
import com.zyd.shiro.business.entity.Org;
import com.zyd.shiro.business.service.SysDeptService;
import com.zyd.shiro.business.service.SysOrgService;
import com.zyd.shiro.business.vo.DeptConditionVO;
import com.zyd.shiro.business.vo.OrgConditionVO;
import com.zyd.shiro.business.vo.OrgSimpleVO;
import com.zyd.shiro.persistence.beans.SysDept;
import com.zyd.shiro.persistence.beans.SysOrg;
import com.zyd.shiro.persistence.mapper.SysDeptMapper;
import com.zyd.shiro.persistence.mapper.SysOrgMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.ArrayList;
import java.util.Arrays;
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
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;



    @Override
    public Dept insert(Dept dept) {
        Assert.notNull(dept, "部门不可为空！");
        dept.setUpdateTime(new Date());
        dept.setCreateTime(new Date());
        sysDeptMapper.insertSelective(dept.getSysDept());
        return dept;
    }




    @Override
    public void insertList(List<Dept> entities) {

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
        return sysDeptMapper.deleteByPrimaryKey(primaryKey) > 0;
    }



    @Override
    public boolean updateSelective(Dept dept) {
        Assert.notNull(dept, "部门不可为空！");
        dept.setUpdateTime(new Date());
        return sysDeptMapper.updateByPrimaryKeySelective(dept.getSysDept()) > 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Dept dept) {
        Assert.notNull(dept, "部门不可为空！");
        dept.setUpdateTime(new Date());

        return sysDeptMapper.updateByPrimaryKey(dept.getSysDept()) > 0;
    }



    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */

    @Override
    public Dept getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(primaryKey);
        return null == sysDept ? null : new Dept(sysDept);
    }


    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param dept
     * @return
     */
    @Override
    public Dept getOneByEntity(Dept dept) {
        Assert.notNull(dept, "部门不可为空！");
        SysDept sysDept = sysDeptMapper.selectOne(dept.getSysDept());
        return null == sysDept ? null : new Dept(sysDept);
    }

    @Override
    public List<Dept> listAll() {
        List<SysDept> sysDepts = sysDeptMapper.selectAll();

        if (CollectionUtils.isEmpty(sysDepts)) {
            return null;
        }
        List<Dept> depts = new ArrayList<>();
        for (SysDept sysDept : sysDepts) {
            depts.add(new Dept(sysDept));
        }
        return depts;
    }


    @Override
    public List<Dept> listByEntity(Dept dept) {
        Assert.notNull(dept, "部门不可为空！");
        List<SysDept> sysDepts = sysDeptMapper.select(dept.getSysDept());
        if (CollectionUtils.isEmpty(sysDepts)) {
            return null;
        }
        List<Dept> depts = new ArrayList<>();
        for (SysDept de : sysDepts) {
            depts.add(new Dept(de));
        }
        return depts;
    }

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Dept> findPageBreakByCondition(DeptConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysDept> sysDepts = sysDeptMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysDepts)) {
            return null;
        }
        List<Dept> depts = new ArrayList<>();
        for (SysDept de : sysDepts) {
            depts.add(new Dept(de));
        }
        PageInfo bean = new PageInfo<SysDept>(sysDepts);
        bean.setList(depts);
        return bean;
    }

    @Override
    public int countByOrgId(Long ids) {
        Weekend<SysDept> weekend = new Weekend<>(SysDept.class);
        //关键字查询部分
        WeekendCriteria<SysDept, Object> keywordCriteria = weekend.weekendCriteria();
        keywordCriteria.andEqualTo(SysDept::getOrgId,ids);
        return sysDeptMapper.selectCountByExample(weekend);
    }


    @Override
    public int countByDeptId(Long[] ids) {
        Weekend<SysDept> weekend = new Weekend<>(SysDept.class);
        //关键字查询部分
        WeekendCriteria<SysDept, Object> keywordCriteria = weekend.weekendCriteria();
        keywordCriteria.andIn(SysDept::getOrgId,Arrays.asList(ids));
        return sysDeptMapper.selectCountByExample(weekend);
    }

    /**
     * 根据用户名查找
     *
     * @param code
     * @return
     */
    @Override
    public Dept getByCode(String code) {
        Dept dept = new Dept(code);
        return getOneByEntity(dept);
    }

    @Override
    public List<SysDept> listByOrgId(long orgId) {
        WeekendSqls<SysDept> sqls = WeekendSqls.<SysDept>custom().andEqualTo(SysDept::getOrgId,orgId);
        List<SysDept> depts = sysDeptMapper.selectByExample(Example.builder(SysDept.class).where(sqls).build());
        return depts;
    }

}
