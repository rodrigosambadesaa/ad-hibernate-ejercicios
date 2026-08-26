package dev.rodrigosambade.hibernate;
import jakarta.persistence.*;
import java.util.*;
@Entity @Table(name="departments") public class Department{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)private Long id;
    @Column(nullable=false,unique=true)private String name;
    @OneToMany(mappedBy="department",cascade=CascadeType.ALL,orphanRemoval=true)private List<Employee> employees=new ArrayList<>();
    protected Department(){}
    public Department(String n){name=Objects.requireNonNull(n);}
    public Long getId(){return id;} public String getName(){return name;} public List<Employee> getEmployees(){return List.copyOf(employees);}
    public void add(Employee e){employees.add(e);e.setDepartment(this);}
}
