package com.qpl.mapper;

import java.util.List;

import com.qpl.entity.PingLun;
import com.qpl.entity.User;

public interface UserMapper{

	int insertUser(User user);

	List<User> selectUser();

	List<PingLun> selectPingLun();

	int insertPingLun(PingLun p);

	
}
