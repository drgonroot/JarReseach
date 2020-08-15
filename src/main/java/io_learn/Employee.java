package io_learn;

import java.time.LocalDate;

/**
 * Created by useheart on 2019-06-04
 *
 * @author useheart
 */
public class Employee extends SerialCloneable {
    // 字节数量
    public static final int RECORD_SIZE = 100;
    // 40个字符 == 80字节
    public static final int NAME_SIZE = 40;
    private static final long serialVersionUID = -4602707844646510416L;
    private final String name;
    // 8个字节
    private double salary;
    // 12字节
    private final LocalDate hireDay;

    public Employee(String name, double salary, int year, int month, int dayOfMonth) {
        this.name = name;
        this.salary = salary;
        this.hireDay = LocalDate.of(year, month, dayOfMonth);
    }


    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }


    public void raiseSalary(double byPercent) {
        double raise = this.salary * byPercent / 100;
        this.salary += raise;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
