package com.qpl.service.Interf;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

	Map uploadXls(MultipartFile multipart);

	Map yijiandaochu();

}
