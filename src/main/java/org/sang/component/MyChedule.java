package org.sang.component;

import org.sang.model.People;
import org.sang.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Component
public class MyChedule {
    @Autowired
    PeopleService peopleService;

    @Scheduled(fixedDelay = 10000)
    public void fixedDelay(){
        System.out.println("fixedDelay" + new Date());
        Calendar cal = Calendar.getInstance();
        StringBuffer buf = new StringBuffer();
        //buf.append(cal.get(Calendar.YEAR)).append("-");
        buf.append(cal.get(Calendar.MONTH)+1).append("-");
        buf.append(cal.get(Calendar.DAY_OF_MONTH)+1);
        System.out.println(buf);
        String birthday = buf.toString();
        List<People> allPeople = peopleService.getAllPeopleByBirthday(birthday);
        System.out.println("明天生日人数：" + allPeople.size());
        Iterator<People> it = allPeople.iterator();
        //输出list对象属性的第一种方式，遍历list，得到list中封装的对象，再通过对象获得属性值
        while (it.hasNext()) {
            People people = (People)it.next();
            System.out.println("人物ID："+people.getId());
            System.out.println("人物姓名："+people.getName());
            System.out.println("人物阳历生日："+people.getSolarCalendarBirthday());
            System.out.println("人物阴历生日"+people.getLunarCalendarBirthday());

        }
    }

    @Scheduled(cron = "0 37 17 ? * *")
    public void fixTimeExecution() {

        System.out.println("在指定时间17时37分执行");
    }
}
