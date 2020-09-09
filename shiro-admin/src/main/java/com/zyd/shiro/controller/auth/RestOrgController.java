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
import com.zyd.shiro.business.entity.Org;
import com.zyd.shiro.business.entity.User;
import com.zyd.shiro.business.enums.ResponseStatus;
import com.zyd.shiro.business.service.SysDeptService;
import com.zyd.shiro.business.service.SysOrgService;
import com.zyd.shiro.business.service.SysUserRoleService;
import com.zyd.shiro.business.service.SysUserService;
import com.zyd.shiro.business.vo.OrgConditionVO;
import com.zyd.shiro.business.vo.OrgSimpleVO;
import com.zyd.shiro.business.vo.UserConditionVO;
import com.zyd.shiro.framework.object.PageResult;
import com.zyd.shiro.framework.object.ResponseVO;
import com.zyd.shiro.persistence.beans.SysDept;
import com.zyd.shiro.util.PasswordUtil;
import com.zyd.shiro.util.ResultUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/org")
public class RestOrgController {
    @Autowired
    private SysOrgService orgService;
    @Autowired
    private SysDeptService deptService;

    @RequiresPermissions("orgs")
    @PostMapping("/list")
    public PageResult list(OrgConditionVO vo) {
        PageInfo<Org> pageInfo = orgService.findPageBreakByCondition(vo);
        return ResultUtil.tablePage(pageInfo);
    }

    @RequiresPermissions("orgs")
    @PostMapping("/simple/list")
    public ResponseVO simplelist(OrgConditionVO vo) {
        List<OrgSimpleVO> list = orgService.simplelist();
        return ResultUtil.success("成功",list);
    }

    @RequiresPermissions("org:add")
    @PostMapping(value = "/add")
    public ResponseVO add(Org org) {
        Org o = orgService.getByCode(org.getCode());
        if (o != null) {
            return ResultUtil.error(500,"该机构代码[" + org.getCode() + "]已存在！请更改");
        }
        try {
            orgService.insert(org);
            return ResultUtil.success("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("error");
        }
    }

    @RequiresPermissions(value = {"org:batchDelete", "org:delete"}, logical = Logical.OR)
    @PostMapping(value = "/remove")
    public ResponseVO remove(Long[] ids) {
        if (null == ids) {
            return ResultUtil.error(500, "请至少选择一条记录");
        }
        if(deptService.countByOrgId(ids) > 0)
            return ResultUtil.error(500, "机构下已有部门！");

        for (Long id : ids) {
            orgService.removeByPrimaryKey(id);
        }
        return ResultUtil.success("成功删除 [" + ids.length + "] 个机构！");
    }

    @RequiresPermissions("org:edit")
    @PostMapping("/get/{id}")
    public ResponseVO get(@PathVariable Long id) {
        return ResultUtil.success(null, this.orgService.getByPrimaryKey(id));
    }

    @RequiresPermissions("org:edit")
    @PostMapping("/edit")
    public ResponseVO edit(Org org) {
        try {
            orgService.updateSelective(org);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("机构修改失败！");
        }
        return ResultUtil.success(ResponseStatus.SUCCESS);
    }

}
