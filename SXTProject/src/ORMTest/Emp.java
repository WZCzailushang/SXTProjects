package ORMTest;

import java.sql.Date;

/**
 * javabean：表和类结构对应
 */

public class Emp {
    private Integer id;
    private String empname;
    private Double salary;
    private Date birthday;
    private Integer age;
    private Integer deptid;


    /**
     * javabean必须要有空参构造
     */
    public Emp() {
    }

    public Emp(String empname, Double salary, Integer age) {
        this.empname = empname;
        this.salary = salary;
        this.age = age;
    }

    public Emp(Integer id, String empname, Double salary, Date birthday, Integer age, Integer deptid) {
        this.id = id;
        this.empname = empname;
        this.salary = salary;
        this.birthday = birthday;
        this.age = age;
        this.deptid = deptid;
    }

    public Emp(String empname, Double salary, Date birthday, Integer age, Integer deptid) {
        this.empname = empname;
        this.salary = salary;
        this.birthday = birthday;
        this.age = age;
        this.deptid = deptid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getDeptid() {
        return deptid;
    }

    public void setDeptid(Integer deptid) {
        this.deptid = deptid;
    }
}
