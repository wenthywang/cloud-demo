package com.cloud.hui.rest;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import cn.hutool.json.JSONObject;

public class RestControllerTest {

	private final static String domain = "http://localhost:8888/people/";

	public static void main(String[] args) {
		getPeoples();
		insertPeople();
		// updatePeople();
		// deletePeople();
	}

	public static void insertPeople() {
		JSONObject json = new JSONObject();
		json.put("name", "rest name");
		json.put("age", 12);
		HttpResponse response = HttpUtil.createPost(domain).body(json).execute();
		System.out.println(response.body());
	}

	public static void getPeoples() {
		HttpResponse response = HttpUtil.createGet(domain).execute();
		System.out.println(response.body());
	}

	public static void updatePeople() {
		String id = "9a29befc-6ede-46f7-856e-c4f61f1f5388";
		String url = domain + id;
		JSONObject json = new JSONObject();
		json.put("name", "rest name update");
		json.put("age", 125);
		HttpResponse response = HttpUtil.createRequest(Method.PUT, url).body(json).execute();
		System.out.println(response.body());
	}

	public static void deletePeople() {
		String id = "9a29befc-6ede-46f7-856e-c4f61f1f5388";
		String url = domain + id;
		HttpResponse response = HttpUtil.createRequest(Method.DELETE, url).execute();
		System.out.println(response.body());
	}

}
