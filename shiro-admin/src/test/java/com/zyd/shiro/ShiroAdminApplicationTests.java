package com.zyd.shiro;

import com.zyd.shiro.entity.ArcModel;
import com.zyd.shiro.mapper.ArcModelMapper;
import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.persistence.mapper.SysUserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ActApplicationTests {

	@Resource
	private ArcModelMapper arcModelMapper;
	@Resource
	private SysUserMapper sysUserMapper;

	@Test
	void contextLoads() {
		List<SysUser> users = sysUserMapper.selectAll();
		System.out.println("######################"+users.size());
	}

}
