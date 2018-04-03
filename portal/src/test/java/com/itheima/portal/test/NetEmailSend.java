package com.itheima.portal.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itheima.utils.NetMailUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class NetEmailSend {

	@Test
	public void test01() {   
		NetMailUtils.sendEmil("kingpen01@163.com", "jihuo","123");
	}
}
