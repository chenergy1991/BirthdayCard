package org.sang.controller;

import org.sang.model.Admin;
import org.sang.model.People;
import org.sang.service.AdminService;
import org.sang.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author sang
 * @date 2018/7/15
 */
@RestController
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    PeopleService peopleService;
    @GetMapping("/adminOps")
    public void adminOps() {
        Admin a1 = new Admin();
        a1.setName("user3");
        a1.setPassword("password3");
        int i = adminService.addAdmin(a1);
        System.out.println("addAdmin>>>" + i);


        Admin a2 = new Admin();
        a2.setId(1);
        a2.setName("user4");
        a2.setPassword("password4");
        int updateAdmin = adminService.updateAdmin(a2);
        System.out.println("updateAdmin>>>"+updateAdmin);


        Admin a3 = adminService.getAdminById(1);
        System.out.println("getAdminById>>>"+a3);
        int delete = adminService.deleteAdminById((2));
        System.out.println("deleteAdminById>>>"+delete);
        List<Admin> allAdmins = adminService.getAllAdmins();
        System.out.println("getAllAdmins>>>"+allAdmins);
    }

    @RequestMapping(value = {"/adminLogin"})
    public ModelAndView adminLogin(@RequestParam("name") String username,
                                   @RequestParam("password") String password,
                                   HttpServletRequest request,
                                   Model model, HttpSession session) {



        Admin admin = adminService.adminLogin(username, password);
        ModelAndView mv = new ModelAndView();
        String url = "fail";
        if(admin!=null){
            System.out.print("登录成功");
            List<People> allPeople = peopleService.getAllPeople();
            mv.addObject("people", allPeople);
            url = "peopleControlPanel";
            session.setAttribute("loginUser", username);
        }
        mv.setViewName(url);
        return mv;
    }
}
