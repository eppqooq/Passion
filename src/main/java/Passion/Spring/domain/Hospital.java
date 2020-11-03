package Passion.Spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hospital
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String name; // 사용자 이름
    private String location; //위치
    private String information1; // 오픈시간 및 마감시간및 점심시간
    private String tel;
    private String information2; //근처 편의시설
    private Integer kind_no; //조인
    private String latitude; // 위도
    private String hardness; // 경도
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }









    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }


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
}
