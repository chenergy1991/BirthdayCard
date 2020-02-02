package org.sang.service;

import org.sang.mapper.PeopleMapper;
import org.sang.model.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *People Service
 * @author Jake J.Chen
 * @date 2019/11/10
 */
@Service
public class PeopleService {
    @Autowired
    PeopleMapper peopleMapper;
    /**
     *增加人物对象
     *
     * @param people 人物对象
     * @return int
     */
    public int addPeople(People people) {
        return peopleMapper.addPeople(people);
    }

    /**
     *通过ID删除人物信息
     *
     * @param id 人物ID
     * @return int
     */
    public int deletePeopleById(Integer id) {
        return peopleMapper.deletePeopleById(id);
    }

    /**
     *更新人物对象
     *
     * @param people 人物对象
     * @return int
     */
    public int updatePeople(People people) {
        return peopleMapper.updatePeopleById(people);
    }

    /**
     *通过ID获得人物对象
     *
     * @param id 人物ID
     * @return List <People>
     */
    public People getPeopleById(Integer id) {
        return peopleMapper.getPeopleById(id);
    }

    /**
     *获得所有人物的列表
     *
     * @return List <People>
     */
    public List<People> getAllPeople() {
        return peopleMapper.getAllPeople();
    }

    /**
     *通过关键字获得人物列表
     *
     * @param keyword 搜索关键字
     * @return List <People>
     */
    public List<People> getAllPeopleByKeyword(String keyword) {
        return peopleMapper.getAllPeopleByKeyword(keyword);
    }

    /**
     *通过关键字获得人物列表
     *
     * @param keyword 搜索关键字
     * @return List <People>
     */
    public List<People> getAllPeopleAndOrderByKeyword(String keyword) {
        return peopleMapper.getAllPeopleAndOrderByKeyword(keyword);
    }

    /**
     *通过生日获得人物列表
     *
     * @param birthday 搜索关键字
     * @return List <People>
     */
    public List<People> getAllPeopleByBirthday(String keyword) {
        return peopleMapper.getAllPeopleByBirthday(keyword);
    }
}
