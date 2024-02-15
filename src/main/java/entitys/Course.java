package entitys;

import jakarta.persistence.*;
import java.util.StringJoiner;


@Entity
@Table(name = "courses")
public class Course {

    //region Поля
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "duration")
    private String duration;
    //endregion

    //region Конструкторы
    public Course(){}

    public Course(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    public Course(int id, String title, String duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }
    //endregion

    //region Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDuration() {
        return duration;
    }
    //endregion

    //region Другие методы
    @Override
    public String toString() {
        return new StringJoiner(", ", "Курс: ", "")
                .add("№ " + getId())
                .add("название " + getTitle())
                .add("продолжительность " + getDuration())
                .toString();
    }
    //endregion
}
