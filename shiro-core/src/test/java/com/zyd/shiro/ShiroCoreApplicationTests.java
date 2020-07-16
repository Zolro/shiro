package com.zyd.shiro;

import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.persistence.mapper.SysUserMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ShiroCoreApplicationTests {
	@Resource
	private SysUserMapper sysUserMapper;
	@Test
	public void contextLoads() {
		SysUser user = sysUserMapper.selectByPrimaryKey(1);
		System.out.println(user.getUsername());
	}

}
