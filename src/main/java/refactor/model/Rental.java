package refactor.model;

/**
 * Created by useheart on 2020/6/13
 *
 * @author useheart
 */
public class Rental {

    private Movie _movie;
    private int _dayRented;

    public Rental(Movie _movie, int _dayRented) {
        this._movie = _movie;
        this._dayRented = _dayRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    public int getDayRented() {
        return _dayRented;
    }

    /**
     * 获取租借的费用
     */
    public double getCharge() {
        double result = 0;
        switch (getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (getDayRented() > 2) {
                    result += (getDayRented()) - 2 * 1.5;
                }
                break;
            case Movie.NEW_RELEASE:
                result += getDayRented() * 3;
                break;
            case Movie.CHILDRENS:
                result += 1.5;
                if (getDayRented() > 3) {
                    result += (getDayRented() - 3) * 1.5;
                }
                break;
            default:
                break;
        }

        return result;
    }
}
