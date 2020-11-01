package Passion.Spring.Form;

import java.util.Date;

public class BoardForm {
    private Long no;
    private Long member_no;
    private String title;
    private String kind_no;
    private String content;
    private String picture;
    private Integer views;
    public String create_day;
    public String update_day;
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

    public String getKind_no() {
        return kind_no;
    }

    public void setKind_no(String kind_no) {
        this.kind_no = kind_no;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getCreate_day() {
        return create_day;
    }

    public void setCreate_day(String create_day) {
        this.create_day = create_day;
    }

    public String getUpdate_day() {
        return update_day;
    }

    public void setUpdate_day(String update_day) {
        this.update_day = update_day;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
