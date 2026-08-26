package dev.rodrigosambade.hibernate;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
class HibernateDemoTest{
    @Test void persistsRelationship(){try(var sf=HibernateDemo.factory();var s=sf.openSession()){var tx=s.beginTransaction();var d=new Department("I+D");d.add(new Employee("Rodrigo",new BigDecimal("1")));s.persist(d);tx.commit();assertEquals(1,s.createQuery("from Employee",Employee.class).getResultList().size());}}
}
