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
import com.zyd.shiro.persistence.beans.SysUserRole;
import com.zyd.shiro.util.PasswordUtil;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Org {

    private SysOrg sysOrg;

    public Org() {
        this.sysOrg = new SysOrg();
    }

    public Org(SysOrg sysOrg) {
        this.sysOrg = sysOrg;
    }

    public SysOrg getSysOrg() {
        return this.sysOrg;
    }

    public Org(String code) {
        this();
        setCode(code);
    }
    public Long getId() {
        return this.sysOrg.getId();
    }

    public void setId(Long id) {
        this.sysOrg.setId(id);
    }

    public String getName() {
        return this.sysOrg.getName();
    }

    public void setName(String name) {
        this.sysOrg.setName(name);
    }


    public String getCode() {
        return this.sysOrg.getCode();
    }

    public void setCode(String code) {
        this.sysOrg.setCode(code);
    }

    public Date getCreateTime() {
        return this.sysOrg.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysOrg.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysOrg.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysOrg.setUpdateTime(updateTime);
    }

}
