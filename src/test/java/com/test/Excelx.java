package com.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.qpl.entity.User;
import com.qpl.utils.WriterExcel;

public class Excelx {

	  public static void main(String[] args) throws IOException {
	        int count = 10;
	        JSONArray ja = new JSONArray();
	        for(int i=0;i<count;i++){
	           User user=new User();
	           user.setUsername("张三"+i);
	           user.setPassword("zhangsan"+i);
	           user.setEmail("张三email"+i);
	           user.setUid(i);
	            ja.add(user);
	        }
	        Map<String,String> headMap = new LinkedHashMap<String,String>();
	        headMap.put("uid","编号");
	        headMap.put("username","姓名");
	        headMap.put("password","密码");
	        headMap.put("email","邮箱");

//	        OutputStream outXls = new FileOutputStream("E://aaaa.xls");
//	        System.out.println("正在导出xls....");
//	        Date d = new Date();
//	        WriterExcel.exportExcel(headMap,ja,null,0,outXls);
//	        System.out.println("共"+count+"条数据,执行"+(new Date().getTime()-d.getTime())+"ms");
//	        outXls.close();
	     
	        OutputStream outXlsx = new FileOutputStream("E://bbbb.xlsx");
	        System.out.println("正在导出xlsx....");
	        Date d2 = new Date();
	        WriterExcel.exportExcelX(headMap,ja,null,0,outXlsx);
	        System.out.println("共"+count+"条数据,执行"+(new Date().getTime()-d2.getTime())+"ms");
	        outXlsx.close();

	    }
}
