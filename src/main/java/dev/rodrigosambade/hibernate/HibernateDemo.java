package dev.rodrigosambade.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;

public final class HibernateDemo {
    private HibernateDemo() {
    }

    public static SessionFactory factory() {
        return new Configuration()
                .addAnnotatedClass(Department.class)
                .addAnnotatedClass(Employee.class)
                .setProperty("hibernate.connection.url", "jdbc:h2:mem:hibernate;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.connection.username", "sa")
                .setProperty("hibernate.hbm2ddl.auto", "create-drop")
                .setProperty("hibernate.show_sql", "false")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = factory();
             Session session = sessionFactory.openSession()) {
            persistSampleData(session);
            printEmployees(session);
        }
    }

    private static void persistSampleData(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            Department department = new Department("Desarrollo");
            department.add(new Employee("Ana", new BigDecimal("2500")));
            session.persist(department);
            transaction.commit();
        } catch (RuntimeException exception) {
            rollbackIfActive(transaction);
            throw exception;
        }
    }

    private static void printEmployees(Session session) {
        session.createQuery(
                        "select e from Employee e join fetch e.department",
                        Employee.class)
                .getResultList()
                .forEach(employee -> System.out.println(
                        employee.getName() + " — " + employee.getDepartment().getName()));
    }

    private static void rollbackIfActive(Transaction transaction) {
        if (transaction.isActive()) {
            transaction.rollback();
        }
    }
}
