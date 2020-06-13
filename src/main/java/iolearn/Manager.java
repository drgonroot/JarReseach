package iolearn;

/**
 * Created by useheart on 2019-06-07
 *
 * @author useheart
 */
public class Manager extends Employee {

    private static final long serialVersionUID = 8875958663105365047L;
    private final Employee secretary;

    public Manager(String name, double salary, int year, int month, int dayOfMonth, Employee secretary) {
        super(name, salary, year, month, dayOfMonth);
        this.secretary = secretary;
    }

    public Employee getSecretary() {
        return secretary;
    }
}
