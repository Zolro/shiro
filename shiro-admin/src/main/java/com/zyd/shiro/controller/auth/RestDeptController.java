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
package com.zyd.shiro.controller.auth;

import com.github.pagehelper.PageInfo;
import com.zyd.shiro.business.entity.Dept;
import com.zyd.shiro.business.entity.Org;
import com.zyd.shiro.business.enums.ResponseStatus;
import com.zyd.shiro.business.service.SysDeptService;
import com.zyd.shiro.business.service.SysOrgService;
import com.zyd.shiro.business.vo.DeptConditionVO;
import com.zyd.shiro.business.vo.OrgConditionVO;
import com.zyd.shiro.business.vo.OrgSimpleVO;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.persistence.beans.SysDept;
import com.zyd.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户管理
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@RestController
@RequestMapping("/dept")
public class RestDeptController {
    @Autowired
    private SysDeptService deptService;


    @RequiresPermissions("depts")
    @PostMapping("/list")
    public PageResult list(DeptConditionVO vo) {
        PageInfo<Dept> pageInfo = deptService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }


    @RequiresPermissions("dept:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Dept dept) {
        Dept d = deptService.getByCode(dept.getCode());
        if (d != null) {
            return ResultUtil.error(500,"该部门代码[" + d.getCode() + "]已存在！请更改");
        }
        try {
            deptService.insert(dept);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @RequiresPermissions(value = {"dept:batchDelete", "dept:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
       /* if(deptService.countByDeptId(ids)> 0)
            return ResultUtil.error(500, "部门下已有子部门！");*/


            deptService.removeByPrimaryKey(ids);

        return ResultUtil.success("成功删除部门！");
    }

    @RequiresPermissions("dept:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.deptService.getByPrimaryKey(id));
    }

    @RequiresPermissions("dept:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Dept dept) {
        try {
            deptService.updateSelective(dept);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("部门修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

    @RequiresPermissions("depts")
    @PostMapping("/org/{orgId}")
    public ResponseVO simplelist(@PathVariable(name="orgId") long orgId) {
        List<SysDept> list = deptService.listByOrgId(orgId);
        return ResultUtil.success("成功",list);
    }

}
