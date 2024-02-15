package entitys;

import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
@Table(name = "students")
public class Student {
    //region Поля
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "numOfCourses")
    private int numOfCoursesCompleted;
    //endregion

    //region Конструкторы
    public Student() {}

    public Student(String name, int age, int numOfCoursesCompleted) {
        this.name = name;
        this.age = age;
        this.numOfCoursesCompleted = numOfCoursesCompleted;
    }

    public Student(int id, String name, int age, int numOfCoursesCompleted) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.numOfCoursesCompleted = numOfCoursesCompleted;
    }
    //endregion

    //region Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNumOfCoursesCompleted() {
        return numOfCoursesCompleted;
    }
    //endregion

    //region Другие методы
    @Override
    public String toString() {
        return new StringJoiner(", ", "Студент: ", "")
                .add("№ " + getId())
                .add("имя " + getName())
                .add("возраст " + getAge())
                .add("количество пройденных курсов " + getNumOfCoursesCompleted())
                .toString();
    }
    //endregion
}

