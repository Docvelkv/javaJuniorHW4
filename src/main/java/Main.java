import entitys.Course;
import entitys.Student;

public class Main {
    public static void main(String[] args) {
        Course course1 = new Course("JavaJunior", "27 дней");
        Course course2 = new Course("JavaCore", "30 дней");
        Student std1 = new Student("Иван", 21, 3);
        Student std2 = new Student("Пётр", 23, 4);

        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Course.class));
        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Student.class));
        System.out.println("*** добавление исходных данных ***");

        HibernateUtil.addObjects(course1, course2);
        HibernateUtil.addObjects(std2, std1);

        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Course.class));
        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Student.class));
        System.out.println("*** удаление объекта из таблицы студентов ***");

        HibernateUtil.deleteObjects(Student.class, 2);
        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Student.class));
        System.out.println("*** обновление элементов в таблицах ***");

        Student std3 = new Student(1, "Максим", 29, 3);
        Course course3 = new Course(1, "JavaJunior", "29 дней");
        HibernateUtil.updateObject(Student.class, 1, std3);
        HibernateUtil.updateObject(Course.class, 1, course3);
        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Student.class));
        HibernateUtil.showObjects(HibernateUtil.getAllRecords(Course.class));
    }
}