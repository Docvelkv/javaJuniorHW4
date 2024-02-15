import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    /**
     * Получение экземпляра фабрики сессий
     * @return SessionFactory
     */
    private static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            try{
                sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .buildSessionFactory();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }

    /**
     * Получение всех записей из таблицы БД в виде списка объектов
     * @param clazz класс объекта
     * @return List<Object>
     */
    public static List<?> getAllRecords(Class<?> clazz){
        List<?> list = new ArrayList<>();
        String query = "FROM " + clazz.getName();
        try (Session session = getSessionFactory().getCurrentSession()){
            Transaction transaction = session.beginTransaction();
            list = session.createQuery(query, clazz).list();
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Вывод списка записей в консоль
     * @param list список объектов
     */
    public static void showObjects(List<?> list){
        list.forEach(System.out::println);
    }

    /**
     * Сохранение объектов в таблицу
     * @param objects сохраняемые объекты
     */
    public static void addObjects(Object... objects){
        try (Session session = getSessionFactory().getCurrentSession()){
            Transaction transaction = session.beginTransaction();
            Arrays.stream(objects).forEach(session::persist);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаление объекта из таблицы по идентификатору
     * @param clazz класс объекта
     * @param id идентификатор(Primary Key)
     */
    public static void deleteObjects(Class<?> clazz, int id){
        try (Session session = getSessionFactory().getCurrentSession()){
            Transaction transaction = session.beginTransaction();
            Object obj = session.get(clazz, id);
            session.remove(obj);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Обновление объекта в таблице
     * @param clazz класс объекта
     * @param id идентификатор(Primary Key)
     * @param newObj новый объект, созданный с помощью полного конструктора
     * с указанием идентификатора
     */
    public static void updateObject(Class<?> clazz, int id, Object newObj){
        try (Session session = getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            Object obj = session.get(clazz, id);
            session.evict(obj);
            obj = session.merge(newObj);
            session.persist(obj);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}