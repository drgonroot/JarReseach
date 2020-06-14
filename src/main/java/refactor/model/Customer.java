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
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            // show figure for this rental
            result += ("\t" + each.getMovie().getTitle() + "\t" + each.getCharge() + "\n");
        }

        // add footer lines
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        Enumeration<Rental> rentals = _rentals.elements();
        String result = "<H1> Rentals for <EM>" + getName() + "</EM></H1><P>\n";
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            // show figure for each rental
            result += each.getMovie().getTitle() + ": " + each.getCharge() + "<BR>\n";
        }
        // add footer lines
        result += "<P> You owe <EM>" + getTotalCharge() + "</EM><P>\n";
        result += "On this rental you earned <EM>" + getTotalFrequentRenterPoints() + "</EM> frequent renter points<P>";
        return result;
    }

    private double getTotalCharge() {
        double result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental rental = rentals.nextElement();
            result += rental.getCharge();
        }
        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;
        Enumeration<Rental> rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = rentals.nextElement();
            result += each.getFrequentRenterPoints();
        }
        return result;
    }
}
