package com.qpl.service.Impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.qpl.entity.PingLun;
import com.qpl.entity.User;
import com.qpl.mapper.UserMapper;
import com.qpl.service.Interf.UserService;
import com.qpl.utils.ReadExcel;
import com.qpl.utils.WriterExcel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	public Map uploadXls(MultipartFile multipart) {
		Map map=new HashMap();
		ReadExcel excel=new ReadExcel();
		List<PingLun> list = excel.getExcelInfoDoList(multipart);
		if(list==null || list.size()==0){
			map.put("status","500");
			map.put("msg", excel.getErrorMsg());
			return map;
		}
		
		for (PingLun p : list) {
			System.out.println(p.toString());
			userMapper.insertPingLun(p);
		}
		map.put("status","200");
		return map;
		
	}

	public Map yijiandaochu() {
		List<PingLun> list = userMapper.selectPingLun();
		Map<String,String> map=new LinkedHashMap<String, String>();
		map.put("pid","评论ID");
		map.put("uname","评论人");
		map.put("pcontent","评论内容");
		map.put("ptime","评论时间");
		
		JSONArray ja=new  JSONArray();
		for (PingLun p :list) {
			ja.add(p);
		}
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream("E:/评论数据.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WriterExcel.exportExcelX(map, ja, null, 0, fs);
		Map m=new HashMap();
		m.put("status", "200");
		return m;
	}
}
