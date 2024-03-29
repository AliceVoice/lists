package pro.sky.lists;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String fullname = firstName + " " + lastName;


    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullname() {
        return fullname;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "ФИО сотрудника: " + getFullname();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(fullname, employee.fullname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname);
    }
}