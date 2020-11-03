package Passion.Spring.Form;

public class HospitalForm {
    private Long no;
    private String name; // 사용자 이름
    private String location; // 위치
    private String information1;
    private String information2;
    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    private Integer kind_no;
    private String Latitude; // 위도
    private String Hardness; //경도
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getHardness() {
        return Hardness;
    }

    public void setHardness(String hardness) {
        Hardness = hardness;
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
