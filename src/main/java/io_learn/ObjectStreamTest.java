package io_learn;

import java.io.*;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Employee harry = new Employee("Harry Hacker", 50000, 1898, 10, 1);
        Manager carl = new Manager("Carl Craker", 80000, 1987, 12, 15, harry);
        Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15, harry);

        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = tony;
        staff[2] = harry;

        // save all employee records to the file employee.dat
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat"))) {
            out.writeObject(staff);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"))) {
            // retrieve all records into a new array
            Employee[] newStaff = (Employee[]) in.readObject();

            //raise secretary's salary
            //newStaff[1].raiseSalary(10)
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
        System.out.println();
    }

}
