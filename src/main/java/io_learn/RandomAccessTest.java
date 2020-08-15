package io_learn;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class RandomAccessTest {

    public static void main(String[] args) throws IOException {
        System.out.println(File.separatorChar);
        System.out.println(System.getProperty("line.separator"));
        System.out.println(System.getProperty("file.encoding"));
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000.0D, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000.0D, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000.0D, 1990, 3, 15);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"))) {
            // save all employee records to the file employee.dat
            for (Employee e : staff) {
                writeData(out, e);
            }
        }

        try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r")) {
            // retrieve all records into a new array
            // compute the array size
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[n];

            // read employees in reverse order
            for (int i = n - 1; i >= 0; i--) {
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            // print the newly read employee records
            for (Employee e : newStaff) {
                System.out.println(e);
            }
        }
    }

    /**
     * Writes employee data to a data output
     *
     * @param out the data output
     * @param e   the employee
     */
    private static void writeData(DataOutput out, Employee e) throws IOException {
        DataIO.writeFixedString(e.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(e.getSalary());

        LocalDate hireDay = e.getHireDay();
        out.writeInt(hireDay.getYear());
        out.writeInt(hireDay.getMonthValue());
        out.writeInt(hireDay.getDayOfMonth());
    }

    private static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m, d);
    }
}
