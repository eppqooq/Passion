package Passion.Spring.controller;

public class HospitalForm {
    private Long no;
    private String name; // 사용자 이름
    private String location; // 위치
    private String information1;
    private String information2;
    private String tel1;
    private String tel2;
    private String tel3;
    private Integer kind_no;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getInformation1() {
        return information1;
    }
    public void setInformation1(String information1) {
        this.information1 = information1;
    }
    public String getInformation2() {
        return information2;
    }
    public void setInformation2(String information2) {
        this.information2 = information2;
    }
    public Integer getKind_no() {
        return kind_no;
    }
    public void setKind_no(Integer kind_no) {
        this.kind_no = kind_no;
    }


    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }


}
