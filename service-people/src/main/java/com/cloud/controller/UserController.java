package com.cloud.controller;

import com.cloud.entity.People;
import com.cloud.util.DBID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.service.PeopleService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@EnableAutoConfiguration
@RestController
@RequestMapping(value = "/people")
public class UserController {
	@Autowired
	private PeopleService peopleService;

	protected static Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(method = RequestMethod.POST)
	public Mono<String> postUser(@RequestBody People p) {

		return Mono.create(resp -> {
			// 处理"/users/"的POST请求，用来创建User
			p.setId(DBID.getID());
			p.setInsertTime(System.currentTimeMillis());
			JSONObject obj = new JSONObject();
			obj.put("status", false);
			obj.put("msg", "添加失败");
			try {
				peopleService.insertPeople(p);
				obj.put("status", true);
				obj.put("msg", "添加成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.success(obj.toString());
		});

	}

	@RequestMapping(method = RequestMethod.GET)
	public Flux<People> getPeople() {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return Flux.create(resp -> {
			peopleService.getPeopleList().forEach(people -> {
				Long insertTime = people.getInsertTime();
				Long updateTime = people.getUpdateTime();
				if (insertTime != null) {
					people.setInsertTime_format(DateUtil.format(DateUtil.date(insertTime), "yyyy-MM-dd HH:mm:ss"));
				}
				if (updateTime != null) {
					people.setUpdateTime_format(DateUtil.format(DateUtil.date(updateTime), "yyyy-MM-dd HH:mm:ss"));
				}
				resp.next(people);
			});
			resp.complete();
		});
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Mono<String> putUser(@PathVariable String id, @RequestBody People p) {

		return Mono.create(resp -> {
			// 处理"/users/{id}"的PUT请求，用来更新User信息
			People pT = peopleService.getPeopleById(id);
			if(pT!=null){
				pT.setUpdateTime(System.currentTimeMillis());
				if (p.getAge() != null) {
					pT.setAge(p.getAge());
				}
				if (StrUtil.isNotBlank(p.getName())) {
					pT.setName(p.getName());
				}
			}else{
				System.out.print("user not found->"+id);
			}

			JSONObject obj = new JSONObject();
			obj.put("status", false);
			obj.put("msg", "添加失败");
			try {
				peopleService.updatePeople(pT);
				obj.put("status", true);
				obj.put("msg", "更新成功");
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.success(obj.toString());
		});

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Mono<String> deleteUser(@PathVariable String id) {
		// 处理"/users/{id}"的DELETE请求，用来删除User
		return Mono.create(resp -> {
			JSONObject obj = new JSONObject();
			obj.put("status", false);
			obj.put("msg", "删除失败");
			try {
				int deleCount = peopleService.deletePeople(id);
				if (deleCount > 0) {
					obj.put("status", true);
					obj.put("msg", "删除成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.success(obj.toString());
		});

	}



	@RequestMapping(value = "/{age}",method = RequestMethod.GET)
	public Flux<People> getPeopleByAge(@PathVariable int age) {
		// 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
		// url中的id可通过@PathVariable绑定到函数的参数中
		return Flux.create(resp -> {
			peopleService.getPeopleListByAge(age).forEach(people -> {
				Long insertTime = people.getInsertTime();
				Long updateTime = people.getUpdateTime();
				if (insertTime != null) {
					people.setInsertTime_format(DateUtil.format(DateUtil.date(insertTime), "yyyy-MM-dd HH:mm:ss"));
				}
				if (updateTime != null) {
					people.setUpdateTime_format(DateUtil.format(DateUtil.date(updateTime), "yyyy-MM-dd HH:mm:ss"));
				}
				resp.next(people);
			});
			resp.complete();
		});
	}

}
