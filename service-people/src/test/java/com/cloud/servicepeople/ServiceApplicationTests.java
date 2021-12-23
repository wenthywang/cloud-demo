package com.cloud.servicepeople;

import java.util.List;
import java.util.UUID;

import com.cloud.servicepeople.entity.People;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cloud.servicepeople.service.PeopleService;

import cn.hutool.json.JSONUtil;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceApplicationTests {
	@Autowired
	private PeopleService peopleService;


	@Test
	public void testGetList() {
		List<People> plist = peopleService.getPeopleList();
		System.out.println(JSONUtil.toJsonStr(plist));
		Assert.assertNotNull(plist);
	}

	@Test
	public void testInsert() {
		People p = new People();
		p.setId(UUID.randomUUID().toString());
		p.setAge(15);
		p.setName("api test");
		p.setInsertTime(System.currentTimeMillis());
		p.setUpdateTime(System.currentTimeMillis());
		peopleService.insertPeople(p);
		Assert.assertTrue(true);
	}

	@Test
	public void testUpdate() {
		People p = new People();
		p.setId("045cc9bd-81ba-49cd-8a96-1afa997c2f4c");
		p.setAge(16);
		p.setName("api test update");
		p.setUpdateTime(System.currentTimeMillis());
		peopleService.updatePeople(p);
		Assert.assertTrue(true);
		testGetList();
	}

	@Test
	public void testDelete() {
		String id = "c388b9ba-049c-4ebd-b751-a113a83e3007";
		peopleService.deletePeople(id);
		Assert.assertTrue(true);
		testGetList();
	}

}
