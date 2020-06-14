package refactor.model;

import java.util.Enumeration;
import java.util.Vector;

/**
 * Created by useheart on 2020/6/13
 *
 * @author useheart
 */
public class Customer {

    private String _name;
    private Vector<Rental> _rentals = new Vector<>();

    public Customer(String _name) {
        this._name = _name;
    }

    public void addRental(Rental arg) {
        this._rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoint = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = rentals.nextElement();

            // determine amounts for each line
            thisAmount = amountFor(each);

            // add frequent renter points
            frequentRenterPoint++;
            // add bonus for two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.CHILDRENS) && each.getDayRented() > 1) {
                frequentRenterPoint++;
            }

            // show figure for this rental
            result += ("\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n");
            totalAmount += thisAmount;
        }

        // add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoint) + " frequent renter points";
        return result;
    }


    /**
     * 计算某个租借的费用
     */
    private double amountFor(Rental rental) {
        return rental.getCharge();
    }
}
