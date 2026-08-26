package dev.rodrigosambade.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HibernateDemoTest {
    @Test
    void persistsRelationship() {
        try (SessionFactory sessionFactory = HibernateDemo.factory();
             Session session = sessionFactory.openSession()) {
            persistDepartmentWithEmployee(session);

            long employeeCount = session
                    .createQuery("select count(e) from Employee e", Long.class)
                    .getSingleResult();

            assertEquals(1L, employeeCount);
        }
    }

    private static void persistDepartmentWithEmployee(Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            Department department = new Department("I+D");
            department.add(new Employee("Rodrigo", new BigDecimal("1")));
            session.persist(department);
            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        }
    }
}
