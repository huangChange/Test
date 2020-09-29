package Ricard.code.domain;



import java.util.Date;

public class Student {
    private String num;
    private String name;
    private String class_num;
    private String department;
    private String province;
    private Date bir;
    private String sex;

    @Override
    public String toString() {
        return "Student{" +
                "num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", class_num='" + class_num + '\'' +
                ", department='" + department + '\'' +
                ", province='" + province + '\'' +
                ", bir=" + bir +
                '}';
    }

    public Student() {}

    public Student(String num, String name, String sex, String class_num, String department, String province, Date bir) {
        this.num = num;
        this.name = name;
        this.sex = sex;
        this.class_num = class_num;
        this.department = department;
        this.province = province;
        this.bir = bir;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClass_num() {
        return class_num;
    }

    public void setClass_num(String class_num) {
        this.class_num = class_num;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }
}
