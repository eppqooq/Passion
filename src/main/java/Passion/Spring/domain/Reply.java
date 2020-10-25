package Passion.Spring.domain;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reply
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;
    private Date regDate;
    private Integer boardNo;
    private Integer memberNo;
    private String content;
    //비회원을 위한
    private String writerName;
    private String password;



}
