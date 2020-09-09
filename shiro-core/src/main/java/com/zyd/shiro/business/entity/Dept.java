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
package com.zyd.shiro.business.entity;

import com.zyd.shiro.persistence.beans.SysDept;
import com.zyd.shiro.persistence.beans.SysOrg;
import com.zyd.shiro.persistence.beans.SysResources;

import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Dept {

    private SysDept sysDept;

    public Dept() {
        this.sysDept = new SysDept();
    }

    public Dept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public Dept(String code) {
        this();
        setCode(code);
    }

    public SysDept getSysDept() {
        return this.sysDept;
    }

    public Long getId() {
        return this.sysDept.getId();
    }

    public void setId(Long id) {
        this.sysDept.setId(id);
    }

    public String getCode() {
        return this.sysDept.getCode();
    }

    public void setCode(String code) {
        this.sysDept.setCode(code);
    }

    public String getName() {
        return this.sysDept.getName();
    }

    public void setName(String name) {
        this.sysDept.setName(name);
    }

    public void setOrgId(Long orgId){this.sysDept.setOrgId(orgId);}

    public Long getOrgId(){ return this.getSysDept().getOrgId();}

    public SysOrg getParent() { return this.sysDept.getParent(); }

    public void setParent(SysOrg org) {
        this.sysDept.setParent(org);
    }

    public Date getCreateTime() {
        return this.sysDept.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysDept.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysDept.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysDept.setUpdateTime(updateTime);
    }



}
