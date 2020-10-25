package Passion.Spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private Long member_no;
    private String title;
    private String kind_name;
    private String picture;
    private Integer views;
    public Date createDay;
    public Date updateDay;

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer available;

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getMember_no() {
        return member_no;
    }

    public void setMember_no(Long member_no) {
        this.member_no = member_no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getUpdateDay() {
        return updateDay;
    }

    public void setUpdateDay(Date updateDay) {
        this.updateDay = updateDay;
    }
}
