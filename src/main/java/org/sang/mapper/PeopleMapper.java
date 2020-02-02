package org.sang.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.sang.model.People;

import java.util.List;

/**
 *People Mapper接口
 * @author Jake J.Chen
 * @date 2019/11/10
 */
@Mapper
public interface PeopleMapper {

    /**
     *增加人物对象
     *
     * @param people 人物对象
     * @return int
     */
    int addPeople(People people);

    /**
     *通过ID删除人物信息
     *
     * @param id 人物ID
     * @return int
     */
    int deletePeopleById(Integer id);

    /**
     *更新人物对象
     *
     * @param people 人物对象
     * @return int
     */
    int updatePeopleById(People people);

    /**
     *通过ID获得人物对象
     *
     * @param id 人物ID
     * @return List <People>
     */
    People getPeopleById(Integer id);

    /**
     *获得所有人物的列表
     *
     * @return List <People>
     */
    List<People> getAllPeople();

    /**
     *通过关键字获得人物列表
     *
     * @param keyword 搜索关键字
     * @return List <People>
     */
    List <People> getAllPeopleByKeyword(String keyword);

    /**
     *获得所有人物的列表，同时按照关键字排序
     *
     * @param keyword 排序关键字
     * @return List <People>
     */
    List <People> getAllPeopleAndOrderByKeyword(@Param("keyword") String keyword);

    /**
     *通过生日获得人物列表
     *
     * @param birthday 搜索关键字
     * @return List <People>
     */
    List <People> getAllPeopleByBirthday(String birthday);
}
