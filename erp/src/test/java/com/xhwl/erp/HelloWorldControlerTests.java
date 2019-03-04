package com.xhwl.erp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xhwl.erp.domain.ProjectReformRepository;
import com.xhwl.erp.domain.ProjectRepository;
import com.xhwl.erp.util.util.MD5Util;
import org.springframework.data.domain.Pageable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldControlerTests {
	
	@Autowired 
	private ProjectRepository projectRepository;
	
	@Test  
    public void hello()  {  
//        java.util.Date utilDate= new java.util.Date();
//		Date sqlDate=new Date(utilDate.getTime());
//	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");  
//	    
//	    String date=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());  
//	    sdf.format(sqlDate);
//	    int radomInt = new Random().nextInt(899999) + 100000;
//		String verificatCode =sdf.format(sqlDate)+ "" + String.valueOf(radomInt);
//		System.err.println(verificatCode);
//		//System.err.println(MD5Util.encode("123456"));
//		String sb = "bbbdsajjds";
//		
//		String date=new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime());  
//		
// 		System.err.println(sdf.format(sqlDate));
//		System.err.println(sqlDate);
    }  
	
	@Test  
    public void index()  {  
		//System.out.println(MD5Util.encode("123456"));
    }  
}