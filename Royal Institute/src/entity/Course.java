package entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Course implements SuperEntity{
    @Id
    private String code;
    private String courseName;
    private String duration;
    private double fee;


    @OneToMany(mappedBy = "course",cascade = CascadeType.ALL)
    private List<Registration> list;

    public Course() {
    }

    public Course(String code, String courseName, double fee, String duration) {
        this.code = code;
        this.courseName = courseName;
        this.fee = fee;
        this.duration = duration;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Registration> getList() {
        return list;
    }

    public void setList(List<Registration> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
