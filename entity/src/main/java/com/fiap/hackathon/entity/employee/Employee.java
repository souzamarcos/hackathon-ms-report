package com.fiap.hackathon.entity.employee;

import java.util.Objects;

public class Employee {
    private String id;
    private String email;
    private String name;
    private EmployeeType type;

    public String getId() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) && Objects.equals(email, employee.email) && Objects.equals(name, employee.name) && type == employee.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, name, type);
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            ", type=" + type +
            '}';
    }

    public Employee(String id, String email, String name, EmployeeType type) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.type = type;
    }
}
