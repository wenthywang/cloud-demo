package com.cloud.dao;

import com.cloud.entity.People;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.annotatoin.SqlResource;
import org.beetl.sql.core.mapper.BaseMapper;

import java.util.List;

@SqlResource("people.people")
public interface PeopleDao extends BaseMapper<People> {

	 List<People> selectByAge(@Param("age") int age);

}
