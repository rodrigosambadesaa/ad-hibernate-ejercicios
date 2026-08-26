package dev.rodrigosambade.hibernate;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import java.math.BigDecimal;
public final class HibernateDemo{
    public static SessionFactory factory(){return new Configuration().addAnnotatedClass(Department.class).addAnnotatedClass(Employee.class).setProperty("hibernate.connection.url","jdbc:h2:mem:hibernate;DB_CLOSE_DELAY=-1").setProperty("hibernate.connection.username","sa").setProperty("hibernate.hbm2ddl.auto","create-drop").setProperty("hibernate.show_sql","false").buildSessionFactory();}
    public static void main(String[]a){try(var sf=factory();var s=sf.openSession()){Transaction tx=s.beginTransaction();try{var d=new Department("Desarrollo");d.add(new Employee("Ana",new BigDecimal("2500")));s.persist(d);tx.commit();}catch(RuntimeException e){tx.rollback();throw e;}s.createQuery("select e from Employee e join fetch e.department",Employee.class).getResultList().forEach(e->System.out.println(e.getName()+" — "+e.getDepartment().getName()));}}
}
