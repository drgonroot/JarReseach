package io_learn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Created by useheart on 2019-06-04
 *
 * @author useheart
 */
public class TextFileTest {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(Charset.defaultCharset());
        System.out.println(Charset.availableCharsets());
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Craker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        //save all employee records to the file employee.dat
        try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8")) {
            writeData(staff, out);
        }

        // retrieve all records into a new array
        try (Scanner in = new Scanner(new FileInputStream("employee.dat"), "UTF-8")) {
            Employee[] newStaff = readData(in);

            //print the newly read employee records
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
        System.out.println();
    }

    /**
     * Writes all employees in an array to a print writer
     *
     * @param employees an array of employees
     * @param out       print writer
     */
    private static void writeData(Employee[] employees, PrintWriter out) {
        // write number of employees
        out.println(employees.length);
        for (Employee e : employees) {
            writeEmployee(out, e);
        }
    }

    /**
     * Reads an array of employees from a scanner
     *
     * @param in the scanner
     * @return the array of employees
     */
    private static Employee[] readData(Scanner in) {
        // retrieve the array size
        int n = in.nextInt();
        in.nextLine(); // consume new line

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++) {
            employees[i] = readEmployee(in);
        }
        return employees;
    }


    /**
     * Writes employee data to a print writer
     *
     * @param out the print writer
     */
    public static void writeEmployee(PrintWriter out, Employee e) {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }

    public static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate hireDate = LocalDate.parse(tokens[2]);
        return new Employee(name, salary, hireDate.getYear(), hireDate.getMonthValue(), hireDate.getDayOfMonth());
    }

}
