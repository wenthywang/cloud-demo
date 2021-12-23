package com.cloud.servicepeople.util;

import java.util.Random;
import java.util.UUID;

public class DBID {

	/**
	 * 用默认的方法获取UUID，长度20位
	 * 
	 * @return
	 */
	public static String getID() {
		return getUUID(true, 20, 8);// 默认替换-，再后加8位随机数
	}

	/**
	 * 获取uuid值，作为主键用
	 * 
	 * @param replaceFlag
	 *            是否替换 - ,true:替换;false:不替换
	 * @param randomLen
	 *            后面补随机数的位数
	 * @return
	 */
	public static String getUUID(boolean replaceFlag, int totalLen, int randomLen) {
		int tmpLen = Math.abs(totalLen - randomLen);

		// 获取uuid
		String uuidStr = UUID.randomUUID().toString().toUpperCase();
		// 取得随机整数
		Random ran = new Random();
		// 取出第一位后的指定几位
		StringBuffer lastBuf = new StringBuffer();
		lastBuf.append(ran.nextInt());
		String temp;
		while (lastBuf.length() < randomLen + 2) {
			// 不够长就继续加
			temp = ran.nextInt() + "";
			lastBuf.append(temp.substring(2, temp.length()));// 防止首位是 -
		}
		uuidStr = uuidStr.substring(0, tmpLen + 1) + lastBuf.toString().substring(2, 2 + randomLen); // 防止首位是
		// -
		if (replaceFlag)
			uuidStr = uuidStr.replace("-", "");
		return uuidStr;
	}
}
