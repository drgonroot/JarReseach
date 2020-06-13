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

    @Deprecated
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoint = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = rentals.nextElement();

            // determine amounts for each line
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDayRented() > 2) {
                        thisAmount += (each.getDayRented()) - 2 * 1.5;
                    }
                    break;
                case Movie.NEW_RELEASE:
                    thisAmount += each.getDayRented() * 3;
                    break;
                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDayRented() > 3) {
                        thisAmount += (each.getDayRented() - 3) * 1.5;
                    }
                    break;
                default:
                    break;
            }

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


    private int amountFor(Rental each) {
        int thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDayRented() > 2) {
                    thisAmount += (each.getDayRented()) - 2 * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDayRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDayRented() > 3) {
                    thisAmount += (each.getDayRented() - 3) * 1.5;
                }
                break;
            default:
                break;
        }

        return thisAmount;
    }


}
