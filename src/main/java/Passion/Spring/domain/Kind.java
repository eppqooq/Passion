package Passion.Spring.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kind
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private String name;
    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
