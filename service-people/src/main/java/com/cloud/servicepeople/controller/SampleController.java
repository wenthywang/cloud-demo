package com.cloud.servicepeople.controller;

import com.cloud.servicepeople.entity.ApplicationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	protected static Logger logger = LoggerFactory.getLogger(SampleController.class);


	@Autowired
	ApplicationConfig applicationConfig;


//	@RequestMapping("/")
//	@ResponseBody
//	String home() {
//		logger.info("[SampleController.home] invoke success!");
//		return "Hello test! i am docker";
//	}
//
//	@RequestMapping("/config")
//	@ResponseBody
//	String getConfig() {
//		logger.info("get config invoke success!");
//		return "你好，我是"+applicationConfig.getName()+",年龄："+applicationConfig.getAge()+"岁。当前环境："+applicationConfig.getVersion();
//	}

	// 这里指定是条状的jsp界面
	@RequestMapping(value = "/test")
	public String index(Model model) {
		model.addAttribute("sb", " hello sb");
		logger.info("[SampleController.index] invoke success!");
		return "test";
	}

}
