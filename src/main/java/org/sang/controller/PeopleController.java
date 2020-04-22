package org.sang.controller;

import org.sang.component.PoiUtils;
import org.sang.model.People;
import org.sang.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *People Mapper接口
 * @author Jake J.Chen
 * @date 2019/11/10
 */
@RestController
public class PeopleController {
    @Autowired
    PeopleService peopleService;
    /**
     * 获取"人物信息控制面板"表单页面
     * @return ModelAndView
     */
    @GetMapping("/peopleControlPanel")
    public ModelAndView peopleList(){
        List<People> allPeople = peopleService.getAllPeople();
        ModelAndView mv = new ModelAndView();
        mv.addObject("people", allPeople);
        mv.setViewName("peopleControlPanel");
        return mv;
    }

    /**
     * 获取"增加人物信息"表单页面
     * @param model
     * @return ModelAndView
     */
    @GetMapping("/peopleAddForm")
    public ModelAndView createForm(Model model) {
        model.addAttribute("people", new People(null, null,null,null));
        model.addAttribute("title", "创建用户");
        return new ModelAndView("peopleAddForm", "peopleModel", model);
    }

    /**
     *获取"增加人物信息"表单页面
     * @param people
     * @return ModelAndView
     */
    @PostMapping("/add")
    public ModelAndView addPeople(People people){
        int result = peopleService.addPeople(people);
        return new ModelAndView("redirect:/peopleControlPanel");
    }

    /**
     *获取"删除人物信息"表单页面
     * @param id
     * @return ModelAndView
     */
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        int result = peopleService.deletePeopleById(id);
        return new ModelAndView("redirect:/peopleControlPanel");
    }

    /**
     *获取"更新人物信息"表单页面
     * @param model
     * @param id
     * @return ModelAndView
     */
    @GetMapping("/update/{id}")
    public ModelAndView update(Model model,@PathVariable int id){
        People people = peopleService.getPeopleById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("people", people);
        mv.setViewName("peopleUpdateForm");
        return mv;
    }

    /**
     *获取"更新人物信息"表单页面
     * @param People
     * @return
     */
    @PostMapping("/update")
    public ModelAndView updateUser(Model model, People people, HttpServletRequest request){
        String id = request.getParameter("id");
        People peopleById = peopleService.getPeopleById(Integer.parseInt(id));
        peopleService.updatePeople(people);
        System.out.println(people);
        return new ModelAndView("redirect:/peopleControlPanel");
    }

    /**
     *获取"搜索人物信息"表单页面
     * @param model
     * @param people
     * @param request
     * @return ModelAndView
     */
    @GetMapping("/search")
    public ModelAndView searchPeople(Model model, People people, HttpServletRequest request){
        String keyword = request.getParameter("k");
        List <People> allPeople = peopleService.getAllPeopleByKeyword(keyword);
        ModelAndView mv = new ModelAndView();
        mv.addObject("people", allPeople);
        mv.setViewName("peopleControlPanel");
        return mv;
    }

    /**
     *获得所有人物的列表，同时按照关键字排序
     * @param model
     * @param people
     * @param request
     * @return ModelAndView
     */
    @GetMapping("/searchAndOrderBy")
    public ModelAndView searchAndOrderPeople(Model model, People people, HttpServletRequest request){
        String keyword = request.getParameter("k");
        List <People> allPeople = peopleService.getAllPeopleAndOrderByKeyword(keyword);
        ModelAndView mv = new ModelAndView();
        mv.addObject("people", allPeople);
        mv.setViewName("peopleControlPanel");
        return mv;
    }

    /**
     *功能描述：增加 人物信息 测试方法（方法的返回值是void）
     * @author Chenergy
     * @date 2019/11/10
     * @param
     * @return
     */
    @GetMapping("/peopleOps")
    public void peopleOps() {
        People people1 = new People();
        people1.setName("JJ Chen");
        people1.setSolarCalendarBirthday("1949-10-01");
        people1.setLunarCalendarBirthday("Good Day");
        int i = peopleService.addPeople(people1);
        System.out.println("addPeople>>>" + i);
        People people2 = new People();
        people2.setName("JJ Lin");
        people2.setSolarCalendarBirthday("1949-10-01");
        people2.setLunarCalendarBirthday("Good Day");
        int j = peopleService.addPeople(people2);
        System.out.println("addPeople>>>" + j);
        /*
        People people2 = new People();
        people1.setId(1);
        people1.setSolarCalendarBirthday("1949-10-01");
        people1.setLunarCalendarBirthday("好日子");
        int j = peopleService.updatePeople(people1);
        System.out.println("updatePeople>>>" + j);

        People people3 = peopleService.getPeopleById(1);
        System.out.println("getPeopleById>>>"+people3);
        int delete = peopleService.deletePeopleById(2);
        System.out.println("deletePeopleById>>>"+delete);
        List<People> allPeople = peopleService.getAllPeople();
        System.out.println("getAllPeople>>>"+allPeople);
        */

    }

    /**
     *功能描述：显示所有人物信息 测试方法（方法的返回值是void）
     * @author Chenergy
     * @date 2019/11/10
     * @param
     * @return
     */
    @GetMapping("/people")
    public ModelAndView books() {
        List<People> allPeople = peopleService.getAllPeople();
        ModelAndView mv = new ModelAndView();
        mv.addObject("people", allPeople);
        mv.setViewName("mypeople");
        return mv;
    }

    /**
     *功能描述：导出所有人物信息到Excel
     * @author Chenergy
     * @date 2020/03/09
     * @param
     * @return
     */
    @GetMapping("/exportPeople")
    public ResponseEntity<byte []> exportPeople() {
        return PoiUtils.exportPeople2Excel(peopleService.getAllPeople());
    }

    /**
     *功能描述：根据Excel导入人物信息
     * @author Chenergy
     * @date 2020/04/08
     * @param
     * @return
     */
    @RequestMapping(value = "/importPeople", method = RequestMethod.POST)
    public String importPeople(MultipartFile file){
        List <People>peopleList = PoiUtils.importPeople2List(file);
        System.out.print("peopleList.size() is" + peopleList.size());
        for(int i = 0; i < peopleList.size();i++)
        {
            peopleService.addPeople(peopleList.get(i));
        }
        return "0";
    }
//    //https://www.javatt.com/p/28620

}
