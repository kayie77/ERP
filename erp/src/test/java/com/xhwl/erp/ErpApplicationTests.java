package com.xhwl.erp;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ErpApplicationTests {

	@Test  
    public void index()  {  
		String a = null;
		String b = null;
		a = "ABC";
		b = a;
		a = "XYZ";
		
		ArrayList<String> aa = new ArrayList<String>();
		aa.add(0, b);
		aa.add(1, a);
		System.err.println(aa);
    }  

}
