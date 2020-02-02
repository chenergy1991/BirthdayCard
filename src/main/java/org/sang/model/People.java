package org.sang.model;

/**
 * Created by sang on 2018/7/15.
 */
public class People {

    /**
     * id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 阳历生日
     */
    private String solarCalendarBirthday;

    /**
     * 阴历生日
     */
    private String lunarCalendarBirthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSolarCalendarBirthday() {
        return solarCalendarBirthday;
    }

    public void setSolarCalendarBirthday(String solarCalendarBirthday) {
        this.solarCalendarBirthday = solarCalendarBirthday;
    }

    public String getLunarCalendarBirthday() {
        return lunarCalendarBirthday;
    }

    public void setLunarCalendarBirthday(String lunarCalendarBirthday) {
        this.lunarCalendarBirthday = lunarCalendarBirthday;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", solarCalendarBirthday='" + solarCalendarBirthday + '\'' +
                ", lunarCalendarBirthday='" + lunarCalendarBirthday + '\'' +
                '}';
    }

    public People(Integer id,String name, String solarCalenderBirthday, String lunarCalenderBirthday) {
        this.id = id;
        this.name = name;
        this.solarCalendarBirthday = solarCalenderBirthday;
        this.lunarCalendarBirthday = lunarCalenderBirthday;
    }

    public People() {
    }
}
