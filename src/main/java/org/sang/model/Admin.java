package org.sang.model;

/**
 * Created by sang on 2018/7/15.
 */
public class Admin {

    /**
     * id
     */
    private Integer id;

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
