package io_learn;

import java.io.*;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class SerialCloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        //clone harry
        Employee harry2 = (Employee) harry.clone();

        // mutate harry
        harry.raiseSalary(10);

        //now harry adn the clone are different
        System.out.println(harry);
        System.out.println(harry2);
    }
}


class SerialCloneable implements Cloneable, Serializable {

    private static final long serialVersionUID = -64724231406938688L;

    /**
     * A class whose clone method uses serialization
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        try {
            //save the object to a byte array
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bout)) {
                out.writeObject(this);
            }
            // read a clone of the object from the byte array
            try (InputStream bin = new ByteArrayInputStream(bout.toByteArray())) {
                ObjectInputStream in = new ObjectInputStream(bin);
                return in.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException e2 = new CloneNotSupportedException();
            e.initCause(e);
            throw e2;
        }

    }
}