package dev.rodrigosambade.hibernate;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@Entity @Table(name="employees") public class Employee{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)private Long id;
    @Column(nullable=false)private String name;
    @Column(nullable=false,precision=12,scale=2)private BigDecimal salary;
    @ManyToOne(fetch=FetchType.LAZY,optional=false)private Department department;
    protected Employee(){}
    public Employee(String n,BigDecimal s){name=Objects.requireNonNull(n);salary=Objects.requireNonNull(s);}
    void setDepartment(Department d){department=d;} public Long getId(){return id;} public String getName(){return name;} public BigDecimal getSalary(){return salary;} public Department getDepartment(){return department;}
}
