package com.cloud.servicepeople.service;

import com.cloud.servicepeople.dao.PeopleDao;
import com.cloud.servicepeople.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleService {
    @Autowired
    private PeopleDao peopleDao;

    public int insertPeople(People people) {
        return peopleDao.getSQLManager().insert(people);
    }

    public List<People> getPeopleList()

    {
        return peopleDao.getSQLManager().all(People.class);
    }

    public int updatePeople(People people) {

        return peopleDao.getSQLManager().updateById(people);
    }

    public int deletePeople(String id) {
        return peopleDao.getSQLManager().deleteById(People.class, id);
    }

    public People getPeopleById(String id) {
        return peopleDao.getSQLManager().unique(People.class, id);
    }

    public List<People> getPeopleListByAge(int age)
    {
        return peopleDao.selectByAge(age);
    }
}
