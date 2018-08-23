package com.cloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

	protected static Logger logger = LoggerFactory.getLogger(SampleController.class);

	@RequestMapping("/")
	@ResponseBody
	String home() {
		logger.info("[SampleController.home] invoke success!");
		return "Hello test!";
	}

	// 这里指定是条状的jsp界面
	@RequestMapping(value = "/test")
	public String index(Model model) {
		model.addAttribute("sb", " hello sb");
		logger.info("[SampleController.index] invoke success!");
		return "test";
	}

}
