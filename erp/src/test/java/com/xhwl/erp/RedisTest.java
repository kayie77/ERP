package com.xhwl.erp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xhwl.erp.entity.Role;
import com.xhwl.erp.util.redis.RedisUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	
//	 @Test
//	 public void setVal(){
//		 Role role = new  Role();
//		 role.setName("测试");
//		 role.setCode("1111");
//	     RedisUtils.set("springboot-test", role, 10000L);
//	 }
	 
	 @Test
	 public void getVal(){
	     System.out.println("**************************" + RedisUtils.get("springboot-test"));
	 }

}
